<%@ page import ="datapro.eibs.master.Util" %>
<html>
<head>
<title>Propuesta de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css"
	rel="stylesheet">

<jsp:useBean id="EDP072101Help" class="datapro.eibs.beans.JBObjList"
	scope="session" />
<jsp:useBean id="EDP072601Help" class="datapro.eibs.beans.JBObjList"
	scope="session" />

<jsp:useBean id="msgRT" class="datapro.eibs.beans.EDP072001Message"
	scope="session" />
<jsp:useBean id="msgRTC" class="datapro.eibs.beans.EDP072201Message"
	scope="session" />
<jsp:useBean id="EDP073701Help" class="datapro.eibs.beans.JBObjList"
	scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"
	scope="session" />

<jsp:useBean id="optLP4" class="datapro.eibs.beans.JBList"
	scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos"
	scope="session" />
<jsp:useBean id="currUser" class="datapro.eibs.beans.ESS0030DSMessage"
	scope="session" />

<jsp:useBean id="ga0726" class="datapro.eibs.beans.JBListRec"
	scope="session" />
<jsp:useBean id="ga0726T" class="datapro.eibs.beans.JBListRec"
	scope="session" />
<jsp:useBean id="gaCodeList" class="datapro.eibs.beans.JBListRec"
	scope="session" />
<jsp:useBean id="gaList" class="datapro.eibs.beans.JBListRec"
	scope="session" />

<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1"
	src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
       builtNewMenu(pc_optionHeader);  

</SCRIPT>



<%if (!error.getERRNUM().equals("0")) {
	error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
		showErrors();
		</SCRIPT>
<%}
out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>


<SCRIPT Language="Javascript">




function callCust() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>"    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}

function callForex() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>"    
page = "<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0395?SCREEN=200&E01REQBRN=999&E01REQCD1=0&E01REQCD2=0&E01REQCD3=0&E01REQCH1=0&E01REQCH2=0&E01REQCH3=0&E01REQREF=0&E01REQCUS="+customer;
CenterNamedWindow(page,'Information',650,500,2);
}

function callPosition() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>"    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5&CUSTOMER="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}

function callGroup() {
var customer = "<%=msgRT.getE01PLPGRP().trim()%>"    
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
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=200&OPT=5&E01IFMCUN="+ <%=msgRT.getE01CUSCUN().trim()%>;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callCupo() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>" ;    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1&customer="+customer;
    	CenterNamedWindow(page,'Information',700,600,2);
}


function callSegP() {
var prop = document.forms[0].E01PLPNPR.value
page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=300&PROP="+ prop;
    	CenterNamedWindow(page,'Information',700,500,2);
}

function callPosition() {
var customer = "<%=msgRT.getE01CUSCUN().trim()%>"    
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5&CUSTOMER="+customer;
    	CenterNamedWindow(page,'Information',650,500,2);
}


function callImpP() {
document.forms[0].PROP.value = document.forms[0].E01PLPNPR.value
document.forms[0].FMT.value = "H";
    document.forms[0].SCREEN.value="730";
	document.forms[0].submit();		  	       	       
}

function callDocum() {
var TABLE_NUM = document.forms[0].TABLE_NUM1.value;   
//alert(TABLE_NUM);
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=P&TABLE_NUM="+TABLE_NUM;
    	CenterNamedWindow(page,'Information',650,500,2);
}

function callPayS() {
var Type = "P";   
page = "<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0160?SCREEN=8&Type="+Type;
    	CenterNamedWindow(page,'Information',700,500,2);
}


function GetCpForm() 
{
	var prop = document.forms[0].E01PLPNPR.value
	page = webapp + "/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=7&E01PLTNPR=" + prop;
	CenterWindow(page,700,500,2);
}

function callDebtOthers() {
//var option = document.forms[0].OPTION.value
var option = '5';
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0800?SCREEN=200&NPR="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&E01DUECUN="
        													+ <%=msgRT.getE01CUSCUN().trim()%>
        													+"&E01DUENA1="
        													+ "<%=msgRT.getE01CUSNA1().trim()%>"
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',650,500,2);
}

function callCreditScor() {
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0883?SCREEN=100&NPR="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&CUN="
        													+ <%=msgRT.getE01CUSCUN().trim()%>
        													+"&CUNAM="
        													+ "<%=msgRT.getE01CUSNA1().trim()%>"
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callAmountCreditScor() {
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0882?SCREEN=100&NPR="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&CUN="
        													+ <%=msgRT.getE01CUSCUN().trim()%>
        													+"&CUNAM="
        													+ "<%=msgRT.getE01CUSNA1().trim()%>"
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callSummaryCreditScor() {
var option = document.forms[0].OPTION.value
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0886?SCREEN=100&NPR="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&CUN="
        													+ <%=msgRT.getE01CUSCUN().trim()%>
        													+"&CUNAM="
        													+ "<%=msgRT.getE01CUSNA1().trim()%>"
        													+"&OPTION="
        													+ option;
    	CenterNamedWindow(page,'Information',700,600,2);
}

//credit rating
function callWarrGen() {
var option = document.forms[0].OPTION.value ;
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0901?SCREEN=100&NPR="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&CUN="
        													+ <%=msgRT.getE01CUSCUN().trim()%>
        													+"&CUNAM="
        													+ "<%=msgRT.getE01CUSNA1().trim()%>"
        													+"&OPTION="
        													+ option
        													+"&TYPE=1";
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callWarrEsp() {
var option = document.forms[0].OPTION.value ;
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0901?SCREEN=100&NPR="+ <%=msgRT.getE01PLPNPR().trim()%>
    														+"&CUN="
        													+ <%=msgRT.getE01CUSCUN().trim()%>
        													+"&CUNAM="
        													+ "<%=msgRT.getE01CUSNA1().trim()%>"
        													+"&OPTION="
        													+ option
        													+"&TYPE=2";
    	CenterNamedWindow(page,'Information',700,600,2);
}

function init(){
 // texto comentarios
 document.forms[0].E02DPAC99.value = "<%=msgRTC.getE02DPAC99().trim()%>";
// document.forms[0].E01DPSCOM.value = "<%=msgRT.getE01DPSCOM().trim()%>";

 // asume siguiente actividad cuando hay una sola actividad para seleccionar si no es apertura
 if (document.forms[0].available != null && document.forms[0].available.value != 0) {
	   var boxLength = document.forms[0].available.length;
	   if (boxLength == 1) {
		   // if (document.forms[0].available.options[1].text == "") {
		   var selectedText = document.forms[0].available.options[0].text;
		   var selectedValue = document.forms[0].available.options[0].value;
		   document.forms[0].E01DPPPXX.value = selectedValue;
		   document.forms[0].E01DPPDXX.value = selectedText;
	   }
  } else {
		//document.forms[0].E01DPPPXX.value = "0001";
		//document.forms[0].E01DPPDXX.value = "APERTURA PROPUESTA";
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

//   var boxLength = document.forms[0].E01PLPAGD.length;
//   i = 0;
//   document.forms[0].E01PLPAGD.selectedIndex=0;
//   if (boxLength != 0) {
//     for (i = 0; i < boxLength; i++) {
//     thisvalue = document.forms[0].E01PLPAGD.options[i].value;
//     if (thisvalue == document.forms[0].AGD.value) {
//	    document.forms[0].E01PLPAGD.selectedIndex=i;
//	    break;
//      }
//     }
//    }
// SOLO MUESTRA GARANTIAS POR ESTADO = 70

   if(("<%=userPO.getHeader16().equals("1")%>")&&("<%=msgRT.getE01PLPPTY().equals("")%>")) { 
		procRecaudoDocumento(true);
		procRegistroGarantia(true);
		procDocGarantia(true);
	} else {		
	    if ("<%=msgRT.getE01PLPPTY().trim()%>" == "1") {	
	    	procRecaudoDocumento(false);
			procRegistroGarantia(false);
			procDocGarantia(false);
		} else {
		    procRecaudoDocumento(true);
		    procRegistroGarantia(true);
		    procDocGarantia(true); 	
		}
	}

	if (document.all["dataTable2"]!=null) {
		resizeDoc2();
	}

	if (document.all["dataTable3"]!=null) {
		resizeDoc3();
	}
 }

function procRecaudoDocumento(value){
 if (value) {
   SecRecaudoDocumento.style.display="none";}
 else{
   SecRecaudoDocumento.style.display=""; 
   }
}

function procRegistroGarantia(value){
 if (<%=msgRT.getE01DPZDXX().trim().equals("1")%>) {
	 if (value) {
	   SecRegistroGarantia.style.display="none";}
	 else{
	   SecRegistroGarantia.style.display=""; 
	   }
 }
}

function procDocGarantia(value){
if (<%=msgRT.getE01DPZDXX().trim().equals("1")%>) {
 if (value) {
   SecDocGarantia.style.display="none";}
 else{
   SecDocGarantia.style.display=""; 
   }
  }
}
 
function goConfirm(opt,TASK, PARAM) {
	document.forms[0].OPECOD.value = "0004";
	document.forms[0].PARAM.value = PARAM;
   	document.forms[0].TASK.value = TASK;

	var op = opt;  	  
	var smsg1 = "Esta seguro que desea ";
	var smsg2 = " el registro seleccionado";
	var emsg1 = "It is sure that desires ";
	var emsg2 = " the record selected";
	document.forms[0].opt.value = op;

	var error = 0
	var smsg = ""
	var emsg = ""
	var smsg1 = ""
	var emsg1 = ""
	
	if ("<%=msgRT.getE01DPPCOM().trim()%>" == "1"){ 
	
	if (document.forms[0].E01DPSCOM.value == "") { 
		smsg1 = 'Especifique comentarios de '+"<%=msgRT.getE01XXXEJ1().trim()%>";
		emsg1 = 'Specify comments of '+"<%=msgRT.getE01XXXEJ1().trim()%>";
		error = 1;
	}
	}

	if (document.forms[0].E02DPAC99.value == "") { 
		smsg = 'Especifique comentarios de Análisis de Credito';
		emsg = 'Specify Credit Analysis comments';
		error = 1;
		document.forms[0].E02DPAC99.focus(); 		
	}

	if (error  == 0){
    
		switch (op) {
			case  "1": 
			    document.forms[0].PARAM.value = "1-1-1";
			    document.forms[0].TASK.value = "EDP0720_proposals_header_maintenance.jsp";
				break; 
			case  "2":
			 	break;   
			case  "3":  
				ok = confirm(smsg1 + " Eliminar " + smsg2);
			    document.forms[0].SCREEN.value="630";
			 	break;
		}
	
		document.forms[0].OPECOD.value = "0004";
	    document.forms[0].SCREEN.value="600";
	
		document.forms[0].submit();		  	       	       

	} else {
		if (document.forms[0].LAN.value == 'S') {
			alert(smsg+"\n\n"+smsg1+"\n\n");
		} else {
			alert(emsg+"\n\n"+emsg1+"\n\n");
		}
	}
}

function goControlDoc(opt) {

	var error = 0;
	var smsg = "";
	var emsg = "";
	var smsg1 = "";
	var smsg2 = "";
	var smsg3 = "";
	var emsg1 = "";
	var emsg2 = "";
	var emsg3 = "";
	
	if (<%=(gaCodeList.getNoResult())%> == true) {
		smsg = 'Debe adicionar un producto para esta propuesta \n\n';
		emsg = 'It should add a product for this proposal\n\n';
		error = 1;
	}

	if (document.forms[0].E01CUSCUN.value == 0) {
		smsg3 = 'Debe Asignar un numero de cliente \n\n';
		emsg3 = 'It Should Assign client number \n\n';
		error = 1;
	}

	if (document.forms[0].E01CUSNA1.value == 0) {
		smsg3 = 'Error en datos del cliente \n\n';
		emsg3 = 'Error in client data \n\n';
		error = 1;
	}

	var op = opt;  
	switch (op) {
	case  "1": 	document.forms[0].OPECOD.value = "0001";
	            document.forms[0].PARAM.value = "1-1-1";
	            document.forms[0].TASK.value = "EDP0720_proposals_header_maintenance.jsp";

	 			break; 
	case  "2":  //ok = confirm(msg1 + " Actualizar " + msg2);
	            //document.forms[0].SCREEN.value="600";
	 			break;   
	case  "3":  if (document.forms[0].LAN.value == 'S') {
					ok = confirm(smsg1 + " Eliminar " + smsg2);
				} else {
					ok = confirm(emsg1 + " Delete " + emsg2);
				};
	            document.forms[0].SCREEN.value="630";
	 			break;
	};

	if (op != "1") {
		if (document.forms[0].E02DPAC99.value == "") { 
			smsg1 = 'Especifique comentarios de Análisis de Credito';
			emsg1 = 'Specify Credit Analysis comments';
			error = 1;
			document.forms[0].E02DPAC99.focus();	
		}

		if ("<%=msgRT.getE01DPPCOM().trim()%>" == "1") {
			if (document.forms[0].E01DPSCOM.value == "") { 
			smsg2 = 'Especifique comentarios de '+"<%=msgRT.getE01XXXEJ1().trim()%>";
			emsg2 = 'Especify comments of '+"<%=msgRT.getE01XXXEJ1().trim()%>";
			error = 1;
			}
		}
	}

	//alert(error);




	if (error  == 0){

    ok = confirm("Esta seguro que desea avanzar a esta actividad?");
	if (ok) {

	document.forms[0].SCREEN.value="600";
	document.forms[0].submit(); 
	}

	} else {
		if (document.forms[0].LAN.value == 'S') {
			alert(smsg+smsg1+smsg2+smsg3);
		} else {
			alert(emsg+emsg1+emsg2+emsg3);
		}
	}
}

function limitText(limitField, limitNum) {

	if (limitField.value.length > limitNum + 1) { 
		if (document.forms[0].LAN.value == 'S') {
			alert('Límite de texto excedido');
		} else {
			alert('Your input has been truncated');
		}	
	}	
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
	}
}

function goCancel(fmt) {
document.forms[0].SCREEN.value="100";
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

// TRABAJA CON CAMPOS CREADOS DINAMICAMENTE
function asigDat(name) {
	var n = name;
	if (n < 10) {
		var DPUBCHK = "document.forms[0].E01DPUB"+n+".checked" ;
		if (eval(DPUBCHK) == true) {
			eval("document.forms[0].E01DPUB"+n+".value = 1");
//			eval("document.forms[0].E01DPDA0"+n+".value = '<%=msgRT.getE01PLPIPD().trim()%>'");
//			eval("document.forms[0].E01DPDM0"+n+".value = '<%=msgRT.getE01PLPIPM().trim()%>'");
//			eval("document.forms[0].E01DPDY0"+n+".value = '<%=msgRT.getE01PLPPLP().trim()%>'");
//		} else {
//			eval("document.forms[0].E01DPUB0"+n+".value = ''");
//			eval("document.forms[0].E01DPDA0"+n+".value = ''");
//			eval("document.forms[0].E01DPDM0"+n+".value = ''");
//			eval("document.forms[0].E01DPDY0"+n+".value = ''");
		}
	} else {
		var DPUBCHK = "document.forms[0].E01DPUB"+n+".checked" ;
		if (eval(DPUBCHK) == true) {
			eval("document.forms[0].E01DPUB"+n+".value = 1");
//			eval("document.forms[0].E01DPDA"+n+".value = '<%=msgRT.getE01PLPIPD().trim()%>'");
//			eval("document.forms[0].E01DPDM"+n+".value = '<%=msgRT.getE01PLPIPM().trim()%>'");
//			eval("document.forms[0].E01DPDY"+n+".value = '<%=msgRT.getE01PLPPLP().trim()%>'");
//		} else {
//			eval("document.forms[0].E01DPUB"+n+".value = ''");
//			eval("document.forms[0].E01DPDA"+n+".value = ''");
//			eval("document.forms[0].E01DPDM"+n+".value = ''");
//			eval("document.forms[0].E01DPDY"+n+".value = ''");
		}
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
              //ingreso 
			  if (document.forms[0].E01CUSCUN.value == 0) {
				alert('Debe Asignar un numero de cliente \n\n');
			  } else {
		   		document.forms[0].OPPRODUCT.value = 1;
	       		document.forms[0].SCREEN.value = 410;
	       		document.forms[0].submit();
			  }
            }
            break;
            //modificion
    case 2 :{
		     document.forms[0].OPPRODUCT.value = 2;
	         document.forms[0].SCREEN.value = 420;
	         document.forms[0].submit();
            }
            break;
    case 3 :{
            //consulta 
             document.forms[0].OPPRODUCT.value = 3;
	         document.forms[0].SCREEN.value = 420;
	         document.forms[0].submit();
            }
            break;
    default : {
            }
            document.forms[0].OPPRODUCT.value = 0;
            break;
    }
 }

function showCollItems(idx,RUT) {
var id = idx
  document.forms[0].COLLCODW.value = id;
  var actTab= document.forms[0].CCOD.value;
  for ( var i=0; i<dataTable.rows.length; i++ ) {
       dataTable.rows[i].className="trnormal";
    }
   dataTable.rows[idx].className="trhighlight"; 
  if (actTab != "") {  
  	document.all["dataTable"+actTab].style.display="none";
  }

	showChecked("COLLCOD");
	resizeDoc();
	window.onresize=resizeDoc;

}

function showCollItemsRD(idx,RUT,TNU) {
var id = idx
  document.forms[0].COLLCODRD.value = id;
  document.forms[0].DOCSEL.value = id;
//  document.forms[0].TABLE_NUM.value = TNU;
//  alert(document.forms[0].TABLE_NUM.value);
  var actTab= document.forms[0].CCODRD.value;
  for ( var i=0; i<dataTableRD.rows.length; i++ ) {
       dataTableRD.rows[i].className="trnormal";
    }
   dataTableRD.rows[idx].className="trhighlight"; 
  if (actTab != "") {  
  	document.all["dataTableRD"+actTab].style.display="none";
  }

	showChecked("COLLCODRD");
//	resizeDoc();
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

// ACTUALIZA GARANTIA
function updGuar(thisValue,prdg,typg,doc){
document.forms[0].PRDG.value = prdg
document.forms[0].TYPG.value = typg
document.forms[0].REGG.value = doc
document.forms[0].TGAR.value = "G"
document.forms[0].SCREEN.value = 710;
document.forms[0].submit();
}
// ACTUALIZA GARANTIA
function updGuarT(thisValue,prdg,typg,doc){
document.forms[0].PRDG.value = prdg
document.forms[0].TYPG.value = typg
document.forms[0].REGG.value = doc
document.forms[0].TGAR.value = "T"
document.forms[0].SCREEN.value = 710;
document.forms[0].submit();
}

// RETRIEVE CUSTOMER INFORMATION FOR NEW PROPOSAL
 function retCustInf(){
   var PROP = document.forms[0].PROP.value
  
    //alert("Tipo propuesta es :"+document.forms[0].E01PLPPTY9.value);
    document.forms[0].E01PLPPTY.value = document.forms[0].E01PLPPTY9.value;
    document.forms[0].SCREEN.value = 720;
	document.forms[0].submit();   
 }

  function goActionDoc(op) {

// alert("Fila seleccionada:" +document.forms[0].DOCSEL.value);
	 
	 if (op == 4) {
	 	var where_to= confirm("¿Está seguro que quiere eliminar los documentos?");
	 	if (where_to == false) {
	 		return;
	 	}
	 }

     document.forms[0].opt.value = op;
//     var formLength= document.forms[0].elements.length;
//     var ok = false;
//     var en = '';
//     for(n=0;n<formLength;n++) {
//      	var elementName= document.forms[0].elements[n].name;
//      	if(elementName == "ROW") {
//		if (document.forms[0].elements[n].checked == true) {
//       		ok = true;
//			en = 'E01DCISTA_' + document.forms[0].elements[n].value;
//       		break;
//		}
//     	}
//    }
//     if ( ok ) {
//       if (op == 2 || op == 4) {
//		for(n=0;n<formLength;n++) {
//    			var l = document.forms[0].elements[n].name;
//      			if(l == en) {
//      				if (op == 2) {
//						document.forms[0].elements[n].value = 'CO';
//					} else if (op == 4) {
//						document.forms[0].elements[n].value = '';
//					}
//					
//				break;
//			}
//		}
// 	}
//    document.forms[0].SCREEN.value = 2;
//	document.forms[0].submit();
//     }
//     else {
//			alert("Seleccione un documento antes de realizar esta operación");	   
//     }

  }

function goDOFA(op) {
	document.forms[0].opt.value = op;
	document.forms[0].SCREEN.value="800";
//	document.forms[0].submit(); 
	page = webapp + "/servlet/datapro.eibs.creditproposal.JSEDP0720?SCREEN=800&opt="
	+ document.forms[0].opt.value ;
	CenterWindow(page,800,600,2);

}

 function goComment(op, observ) {
	var observ = observ
	alert(observ);
   //document.forms[0].opt.value = op;
   switch (op) {
    case 1 :{
			ShowAMT();
            break;
            }
  }
 }



 function cancelBub(){
  event.cancelBubble = true;
 }


 function ShowAMT() {	 
	 var y= dataTableRD.offsetTop + 700;
//	 alert(y);
//     var x= dataTableRD.offsetLeft + (AMT.clientWidth /2);
     var x= dataTableRD.offsetLeft ;
//	 alert(x);
	 //cancelBub();
	 eval('AMT.style.pixelTop=' + y);
     eval('AMT.style.pixelLeft=' + x);
	 AMT.filters[0].apply();
     AMT.style.visibility="visible";
     AMT.filters[0].Play();
	 //document.forms[0].RUTNUM.focus();
 }

 function hideAMT(){
      AMT.style.visibility="hidden";
 }


function goRetrComm(){
 // traer texto comentarios
 document.forms[0].E01DPSCOM.value = "<%=msgRT.getE01DPSCOM().trim()%>";
 }



document.onclick= hideAMT;




</script>


</head>
<body onload=javascript:init()>
<%if (!error.getERRNUM().equals("0")) {
	out.println("<SCRIPT Language=\"Javascript\">");
	error.setERRNUM("0");
	out.println("       showErrors()");
	out.println("</SCRIPT>");
}
//     
%>
<H3 align="center">Propuesta de Cr&eacute;dito<img
	src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left"
	name="EIBS_GIF" ALT="proposals_header_maintenance.jsp, EDP0720"></H3>

<%if (userPO.getHeader16().equals("1")) {%>
<P align="center"><INPUT type="text" name="DSC1" size="35"
	maxlength="35"
	value="<%if (userPO.getHeader16().equals("1")) {
	out.print("APERTURA NUEVA PROPUESTA");
}%>"
	readonly></P>
<%} else {%>

<P align="center"><INPUT type="text" name="DSC" size="35" maxlength="35"
	value="<%=msgRT.getE01PLPPSR()%>-<%=msgRT.getE01DESAC2()%>" readonly></P>

<%};%>


<form method="post"
	action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600"> <INPUT TYPE=HIDDEN
	NAME="OPTION" VALUE="<%=userPO.getOption()%>"> <INPUT TYPE=HIDDEN
	NAME="opt" VALUE="<%=userPO.getHeader16().trim()%>"> <INPUT TYPE=HIDDEN
	NAME="optH" VALUE=""> <INPUT TYPE=HIDDEN NAME="optH1" VALUE=""> <INPUT
	TYPE=HIDDEN NAME="PROP" VALUE=""> <INPUT TYPE=HIDDEN NAME="FMT"
	VALUE=""> <INPUT TYPE=HIDDEN NAME="OPPRODUCT" VALUE=""> <INPUT
	type=HIDDEN name="E01PLPPTY" value="<%=msgRT.getE01PLPPTY().trim()%>">
<INPUT type=HIDDEN name="TIPPROPUESTA"
	value="<%=msgRT.getE01PLPPTY().trim()%>"> <input type=HIDDEN
	name="OPECOD" value=""> <input type=HIDDEN name="PARAM"
	value="<%=msgRT.getE01DPWPAR().trim()%>"> <input type=HIDDEN
	name="TASK" value="<%=userPO.getHeader23().trim()%>"> <input
	type=HIDDEN name="E01YYYFIL" value="<%=msgRT.getE01YYYFIL().trim()%>">

<input type=HIDDEN name="AGD" value="<%=msgRT.getE01PLPAGD().trim()%>">
<input type=HIDDEN name="pos" value="<%=msgRT.getE01RECPOS().trim()%>">

<INPUT TYPE=HIDDEN NAME="COLLCODW" VALUE=""> <INPUT TYPE=HIDDEN
	NAME="CCOD" VALUE=""> <INPUT TYPE=HIDDEN NAME="ICOD" VALUE=""> <INPUT
	TYPE=HIDDEN NAME="ROW" VALUE="0"> <INPUT TYPE=HIDDEN NAME="PRDG"
	VALUE=""> <INPUT TYPE=HIDDEN NAME="TYPG" VALUE=""> <INPUT TYPE=HIDDEN
	NAME="REGG" VALUE=""> <INPUT TYPE=HIDDEN NAME="TGAR" VALUE=""> <INPUT
	TYPE=HIDDEN NAME="CCODRD" VALUE=""> <INPUT TYPE=HIDDEN NAME="DOCSEL"
	VALUE=""> <INPUT TYPE=HIDDEN NAME="TABLE_NUM" VALUE=""> <INPUT
	TYPE=HIDDEN NAME="LAN" value="<%=msgRT.getE01CNTLAN().trim()%>"> <input
	type=HIDDEN name="E01DPWOPC" value="<%=msgRT.getE01DPWOPC().trim()%>">

<input type=HIDDEN name="E01PLPRUT"
	value="<%=msgRT.getE01PLPRUT().trim()%>"> <%if (userPO.getHeader16().equals("1")) {%>
<input type=HIDDEN name="E01DPPPXX" value=""> <input type=HIDDEN
	name="E01DPPDXX" value=""> <input type=HIDDEN name="E01PLPSRU"
	value="<%=msgRT.getE01PLPSRU().trim()%>"> <input type=HIDDEN
	name="E01PLPACT" value="<%=msgRT.getE01PLPACT().trim()%>"> <%};%>


<div id="AMT"
	style="position: absolute; visibility: hidden; left: 0px; top: -10px; z-index: 3; filter: progid : DXImageTransform . Microsoft . Fade(duration=1 .0, overlap=1 .0)"
	onclick="cancelBub()">
<TABLE id=tbhelp
	style="border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-color: #0b23b5; border-style: solid solid solid solid; filter: progid : DXImageTransform . Microsoft . dropshadow(OffX=3, OffY=3, Color='gray', Positive='true')">
	<tr bordercolor="#FFFFFF">
		<td nowrap width="750">

		<TABLE id="tbenter" align="center" style="width: 100%" border="0">
			<TBODY>
				<TR>
					<TD width="100%">
					<div align="left">Observacion:</div>
					<input type="text" name="E01DPOBSR" size="81" maxlength="80"
						value=""
						<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>>
					</TD>
				</TR>


			</TBODY>
		</TABLE>
		</td>
	</tr>
</TABLE>
</div>









<h4><A NAME="Miscelaneos"> Propuesta</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="3" width="100%" border="0">

			<tr id="trdark">
				<td width="15%">
				<div align="right">Tipo de Propuesta</div>
				</td>

				<td width="30%" colspan="1"><INPUT type="hidden" name="TYPEPROPOSAL"
					size="2" maxlength="2" value="<%=msgRTC.getE01COMIV5().trim()%>"
					readonly> <%-- APERTURA --%> <input type="radio" name="E01PLPPTY9"
					value="1" disabled
					<%if (msgRT.getE01PLPPTY().equals("1"))
	out.print("checked");%>>
				Solicitud <%--           
              	  <input type="radio" name="E01PLPPTY9" value="7" disabled <%if(msgRT.getE01PLPPTY().equals("7")) out.print("checked");%>>
              		Otras Operaciones				
			   --%></td>

				<td width="50%" align="left">Estado Propuesta: <INPUT type="text"
					name="E01XXXEST" size="35" maxlength="35"
					value="<%=msgRT.getE01XXXEST().trim()%>" readonly></td>

			</tr>

			<tr id="trdark">
				<td width="15%">
				<div align="right">N&uacute;mero:</div>
				</td>

				<td width="30%"><INPUT type="text" name="E01PLPNPR" size="12"
					maxlength="12" value="<%=msgRT.getE01PLPNPR().trim()%>" readonly>
				Ref: <INPUT type="text" name="E01PLPRRE" size="12" maxlength="12"
					value="<%=msgRT.getE01PLPRRE().trim()%>" readonly></td>

				<td width="50%" align="left">Fecha Apertura: <INPUT type="text"
					name="E01PLPIPD" size="2" maxlength="2"
					value="<%=msgRT.getE01PLPIPD().trim()%>" readonly> <INPUT
					type="text" name="E01PLPIPM" size="2" maxlength="2"
					value="<%=msgRT.getE01PLPIPM().trim()%>" readonly> <INPUT
					type="text" name="E01PLPPLP" size="2" maxlength="2"
					value="<%=msgRT.getE01PLPPLP().trim()%>" readonly></td>
			</tr>

			<tr id="trdark">
				<td width="15%" align="right">Banco</td>
				<td width="30%"><%if (userPO.getHeader16().equals("x")) {%> <input
					type="text" name="E01PLPBNK" size="3" maxlength="2" value=""
					onKeypress="enterInteger()"> <a
					href="javascript:GetBank('E01PLPBNK','')"> <img
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0"></a> <%} else {%> <input type="text"
					name="E01PLPBNK" size="3" maxlength="2"
					value="<%=msgRT.getE01PLPBNK().trim()%>" readonly> <%};%></td>
				<td width="50%" align="left">Sucursal de Apertura: <input
					type="text" name="E01PLPBRN" size="5" maxlength="4"
					value="<%=msgRT.getE01PLPBRN().trim()%>" readonly></td>

			</tr>

			<tr id="trdark">
				<td width="15%">
				<div align="right">C&oacute;digo Ejecutivo:</div>
				</td>
				<td width="30%"><input type="text" name="E01PLPEJE" size="5"
					maxlength="4" value="<%=msgRT.getE01PLPEJE().trim()%>" readonly> <INPUT
					type="text" readonly name="E01XXXEJE" size="24" maxlength="24"
					value="<%=msgRT.getE01XXXEJE().trim()%>"></td>
				<td width="50%" align="left">Sujeto de Crédito <input type="text"
					name="E01PLPCN2" size="5" maxlength="4"
					value="<%=msgRT.getE01PLPCN2().trim()%>"
					<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>>
				<%if (!userPO.getHeader16().equals("5")) {%> <A
					href="javascript:GetCodeDescCNOFC('E01PLPCN2','E01PLPRE2','PD')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0"> </A> <%}%> <INPUT type="text"
					name="E01PLPRE2" size="15" maxlength="15"
					value="<%=msgRT.getE01PLPRE2().trim()%>" readonly></td>
			</tr>
			<tr id="trdark">
				<td width="15%"></td>
				<td width="30%"></td>
				<td width="50%" align="left">Destino de Crédito <input type="text"
					name="E01PLPCN3" size="5" maxlength="4"
					value="<%=msgRT.getE01PLPCN3().trim()%>"
					<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>>
				<%if (!userPO.getHeader16().equals("5")) {%> <A
					href="javascript:GetCodeDescCNOFC('E01PLPCN3','E01PLPRE3','38')"><IMG
					src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
					align="bottom" border="0"> </A> <%}%> <INPUT type="text"
					name="E01PLPRE3" size="15" maxlength="15"
					value="<%=msgRT.getE01PLPRE3().trim()%>" readonly></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<h4>Datos del Cliente</h4>
<table class="tableinfo">
	<tr>
		<td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trdark">
				<td width="20%">
				<div align="right">N&uacute;mero Cliente :</div>
				</td>
					<td width="30%">
					<%if (userPO.getHeader16().equals("1")) {%> 
					<input type=TEXT name="E01CUSCUN" size=10 maxlength=9 onKeypress="enterInteger()" value="<%=msgRT.getE01CUSCUN().trim()%>" onblur="retCustInf();"> 
					<a href="javascript:GetCustomerDescId('E01CUSCUN','E01CUSNA1','')">
					<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0">
					</a> 
					<%} else {%> 
					<input type="text" name="E01CUSCUN" size="27" maxlength="4" value="<%=msgRT.getE01CUSCUN().trim()%>" readonly> <%};%>
				</td>
				<td width="20%">
				<div align="right">Nombre Cliente :</div>
				</td>
				<td width="30%"><input type="text" name="E01CUSNA1" size="27"
					maxlength="4" value="<%=msgRT.getE01CUSNA1().trim()%>" readonly></td>
			</tr>
			<tr id="trdark">
				<td width="20%">
				<div align="right">Clasificación:</div>
				</td>
				<td width="30%"><input type="text" name="E01CUFCL1" size="27"
					maxlength="35" value="<%=msgRT.getE01CUFCL1().trim()%>" readonly></td>
				<td width="20%" align="right"></td>
				<td width="30%"></td>
			</tr>
			<tr id="trdark">
				<td width="20%">
				<div align="right">Grupo:</div>
				</td>
				<td width="30%"><INPUT type="TEXT" name="E01PLPGRP" size="10"
					maxlength="9" onkeypress="enterInteger()"
					value="<%=msgRT.getE01PLPGRP().trim()%>" readonly> <A
					name="Miscelaneos0"> <INPUT type="text" name="E01XXXGR1" size="27"
					maxlength="4" value="<%=msgRT.getE01XXXGR1().trim()%>" readonly></A></td>
				<td width="20%">
				<div align="right">Actividad Económica:</div>
				</td>
				<td width="30%"><INPUT type="TEXT" name="E01PLPBUC" size="4"
					maxlength="4" onkeypress="enterInteger()" readonly
					value="<%=msgRT.getE01PLPBUC().trim()%>"> <input type="text"
					name="E01XXXBUC" size="27" maxlength="4"
					value="<%=msgRT.getE01XXXBUC().trim()%>" readonly></td>
			</tr>
			<tr id="trdark">
				<td width="20%">
				<div align="right">Fecha Nacimiento/Constitución:</div>
				</td>
				<td width="30%"><INPUT type="text" name="E01CUSIDD" size="2"
					maxlength="2" value="<%=msgRT.getE01CUSIDD().trim()%>" readonly> <INPUT
					type="text" name="E01CUSIDM" size="2" maxlength="2"
					value="<%=msgRT.getE01CUSIDM().trim()%>" readonly> <INPUT
					type="text" name="E01CUSIDY" size="2" maxlength="2"
					value="<%=msgRT.getE01CUSIDY().trim()%>" readonly></td>
				<td width="20%" align="right">Fecha Vencimiento:</td>
				<td width="30%"><INPUT type="text" name="E01XXXVAD" size="2"
					maxlength="2" value="<%=msgRT.getE01XXXVAD().trim()%>" readonly> <INPUT
					type="text" name="E01XXXVAM" size="2" maxlength="2"
					value="<%=msgRT.getE01XXXVAM().trim()%>" readonly> <INPUT
					type="text" name="E01XXXVAY" size="2" maxlength="2"
					value="<%=msgRT.getE01XXXVAY().trim()%>" readonly></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<A NAME="producto">
<CENTER><A HREF="#Miscelaneos">Misceláneos</A> &nbsp; |&nbsp; <A
	HREF="#producto">Producto</A> &nbsp; |&nbsp; <A HREF="#recaudos">Recaudos</A>
&nbsp; |&nbsp; <A HREF="#comentarios">Comentarios Analista</A> &nbsp;
|&nbsp;</CENTER>




<%if (gaCodeList.getNoResult()) {%>
<TABLE class="tbenter" height="20%">
	<h4>Producto</h4>
	<TR>
		<TD ALIGN=CENTER VALIGN=MIDDLE>
		<H4 style="text-align: center">No existe ningun Producto creado para
		esta propuesta. <br>
		</h4>
		</TD>
	</TR>
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
				<%if ((msgRT.getE01SWMPRD().trim().equals("1"))
	&& (!userPO.getHeader16().equals("5"))) {%>
				<td class=TDBKG width="20%">
				<div align="center"><a href="javascript:goActionProd(1)"><b>Nuevo</b></a></div>
				</td>
				<%}%>
				<%if (!msgRT.getE01CRTEST().equals("AA")) {%>
				<%if (!userPO.getHeader16().equals("5")
	&& (!msgRT.getE01CRTEST().equals("AA"))) {%>
				<td class=TDBKG width="20%">
				<div align="center"><a href="javascript:goActionProd(2)"><b>Modificar</b></a></div>
				<%}%> <%}%>
				<td class=TDBKG width="20%">
				<div align="center"><a href="javascript:goActionProd(3)"><b>Consultar</b></a></div>
			</tr>
		</table>
	</TR>

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
				<div align="right">Monto Solicitado</div>
				</th>
				<th align=CENTER nowrap width="20%"><%if (!msgRT.getE01CRTEST().equals("AA")) {%>
				<div align="right">Monto Recomendado</div>
				<%} else {%>
				<div align="right">Monto Aprobado</div>
				<%}%></th>
				<%--  <th align=CENTER nowrap width="10%">
				  <div align="right">Ruta</div>
				  </th>
				 --%>
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
				<TD ALIGN=LEFT NOWRAP width="5%"><input type="radio" name="COLLCOD"
					value="<%=EDP072101Help.getCurrentRow()%>"
					onClick="showCollItems(this.value,'<%=msgList.getE01PLTRU0()%>')"
					<%=gaChk%>></TD>
				<TD ALIGN=CENTER NOWRAP width="5%"><a
					href="javascript:radioClick('COLLCOD',<%=i%>)">
				<DIV NOWRAP><%=gaCodeList.getRecord(0)%></DIV>
				</a></TD>
				<TD ALIGN=CENTER NOWRAP width="10%"><a
					href="javascript:radioClick('COLLCOD',<%=i%>)">
				<DIV NOWRAP><%=gaCodeList.getRecord(1)%></DIV>
				</a></TD>
				<TD ALIGN=LEFT NOWRAP width="15%"><a
					href="javascript:radioClick('COLLCOD',<%=i%>)">
				<DIV NOWRAP><%=gaCodeList.getRecord(2)%></DIV>
				</a></TD>
				<TD ALIGN=CENTER NOWRAP width="20%"><a
					href="javascript:radioClick('COLLCOD',<%=i%>)">
				<DIV NOWRAP><%=gaCodeList.getRecord(3)%></DIV>
				</a></TD>
				<TD ALIGN=CENTER NOWRAP width="20%"><a
					href="javascript:radioClick('COLLCOD',<%=i%>)">
				<DIV NOWRAP><%=gaCodeList.getRecord(4)%></DIV>
				</a></TD>
				<%--
					    <TD ALIGN=CENTER NOWRAP width="10%" >
					    <a href="javascript:radioClick('COLLCOD',<%=i%>)" >
					    <DIV NOWRAP><%=gaCodeList.getRecord(5)%></DIV>
				  	    </a></TD>
                    --%>
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
	code = gaList.getRecord(1);%> <SCRIPT language="JavaScript">
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
   </SCRIPT> <%}%> <SCRIPT language="JavaScript">

     function resizeDoc() {
      var actvTb = document.forms[0].CCOD.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
    }
	showChecked("COLLCOD");
	resizeDoc();
	window.onresize=resizeDoc;

     function resizeDocRD() {
      var actvTb = document.forms[0].CCODRD.value;
      var dataT = document.all["dataTableRD"+actvTb];
       adjustEquTables(headTable1, dataTableRD, dataDiv1,1,false);
    }
	showChecked("COLLCOD");
	resizeDoc();
	window.onresize=resizeDoc;
     
	</SCRIPT> <%}%> <A NAME="recaudos">

<CENTER><A HREF="#Miscelaneos">Misceláneos</A> &nbsp; |&nbsp; <A
	HREF="#producto">Producto</A> &nbsp; |&nbsp; <A HREF="#recaudos">Recaudos</A>
&nbsp; |&nbsp; <A HREF="#comentarios">Comentarios Analista</A> &nbsp;
|&nbsp;</CENTER>


<div id="SecRecaudoDocumento" style="display: none">Seccion Recaudo
Documentos</DIV>

<A NAME="RecaudoDocumento">
<h4 align="left">Recaudo Documentos</h4>
<%--
  <TABLE class="tbenter" width="100%">
    <TR> 
      <TD ALIGN=CENTER  class=TDBKG> <a href="javascript:goActionDoc(1)"><b>Actualizar</b></a> 
      </TD>
      <TD ALIGN=CENTER  class=TDBKG> <a href="javascript:goActionDoc(2)"><b>Escanear</b></a> 
      </TD>
      <TD ALIGN=CENTER   class=TDBKG> <a href="javascript:goActionDoc(3)"><b>Visualizar</b></a> 
      </TD>
      <TD ALIGN=CENTER   class=TDBKG> <a href="javascript:goActionDoc(4)"><b>Limpiar</b></a> 
      </TD>
    </TR>
  </TABLE>
--%>

<div id="dataDiv3" align="center"
	style="height: 200; overflow-y: scroll; width: 100%; padding-left: 10; padding-right: 5"
	class="scbarcolor">

<TABLE class="tableinfo" id="dataTableRD" width="100%">
	<TR id=trdark>
		<TD ALIGN=LEFT NOWRAP width="5%"><b>Sel</b></TD>
		<TD ALIGN="center" NOWRAP width="70%"><b>Documento</b></TD>
		<TD ALIGN="center" NOWRAP width="15%"><b>Check</b></TD>
		<TD ALIGN=center NOWRAP width="10%"><b>Condición</b></TD>
	</TR>

	<%EDP073701Help.initRow();
int recnum = 1;
String gaChkRD = "";
String name = "";
boolean firstTimeRD = true;
while (EDP073701Help.getNextRow()) {
	gaChkRD = (firstTimeRD) ? "checked" : "";
	firstTimeRD = false;
	if (recnum < 10)
		name = "" + recnum;
	else
		name = "" + recnum;
	datapro.eibs.beans.EDP073701Message msgRTR =
		(datapro.eibs.beans.EDP073701Message) EDP073701Help.getRecord();%>

	<TR id=trclear>

		<TD ALIGN=LEFT NOWRAP width="5%"><input type="radio" name="COLLCODRD"
			value="<%=name%>"
			onClick="showCollItemsRD(this.value,'<%=msgRTR.getField("E01DPDD01").getString().trim()%>','<%=msgRTR.getField("E01DPDTNU").getString().trim()%>')"
			<%=gaChkRD%>></TD>
		<TD ALIGN="left" NOWRAP width="40%"><INPUT type="hidden"
			name="E01PLTPRO<%=name%>"
			value="<%=msgRTR.getField("E01PLTPRO").getString().trim()%>"> <INPUT
			type="hidden" name="E01DPDD<%=name%>"
			value="<%=msgRTR.getField("E01DPDD01").getString().trim()%>"> <INPUT
			type="hidden" name="E01DPDT<%=name%>"
			value="<%=msgRTR.getField("E01DPDT01").getString().trim()%>"> <INPUT
			type="text" maxlength="40" size="40" name="E01DPDX<%=name%>"
			value="<%=msgRTR.getField("E01DPDX01").getString().trim()%>" readonly
			width="20%"> <INPUT type="text" name="E01DPOB<%=name%>" size="81"
			maxlength="80"
			value='<%=msgRTR.getField("E01DPOBSR").getString().trim()%>'
			<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>><INPUT
			type="text" maxlength="10" size="10" name="NIVDOC<%=name%>"
			<%if (msgRTR.getField("E01DPDT01").getString().trim().equals("1")) {%>
			value="REQUERIDO" <%};%>
			<%if (msgRTR.getField("E01DPDT01").getString().trim().equals("2")) {%>
			value="ADICIONAL" <%};%>
			<%if (msgRTR.getField("E01DPDT01").getString().trim().equals("3")) {%>
			value="OPCIONAL" <%};%> readonly width="10%"></TD>
		<TD ALIGN=center NOWRAP width="15%"><INPUT type="checkbox"
			name="E01DPUB<%=name%>"
			value="<%=msgRTR.getField("E01DPUB01").getString().trim()%>"
			<%if (!msgRTR.getField("E01DPUB01").getString().trim().equals(""))
	out.print("checked");%>
			width="5%" onClick="asigDat(<%=name%>);"
			<%if (userPO.getHeader16().equals("5")) {
	out.print("DISABLED");
}%>>
		</TD>
		<TD ALIGN=center NOWRAP width="10%"><SELECT name="E01DPFR<%=name%>"
			<%if (userPO.getHeader16().equals("5")) {
	out.print("DISABLED");
}%>>
			<option value=" "
				<%if (msgRTR.getField("E01DPFR01").getString().equals(" ")) {
	out.print("selected");
}%>></option>
			<option value="1"
				<%if (msgRTR.getField("E01DPFR01").getString().equals("1")) {
	out.print("selected");
}%>>Conforme</option>
			<option value="2"
				<%if (msgRTR.getField("E01DPFR01").getString().equals("2")) {
	out.print("selected");
}%>>Incompleto</option>
			<option value="3"
				<%if (msgRTR.getField("E01DPFR01").getString().equals("3")) {
	out.print("selected");
}%>>Ilegible</option>
			<option value="4"
				<%if (msgRTR.getField("E01DPFR01").getString().equals("4")) {
	out.print("selected");
}%>>Faltante</option>
		</SELECT>
		<INPUT type="hidden" name="TABLE_NUM<%=name%>" value="<%=msgRTR.getField("E01DPDTNU").getString().trim()%>"> 
	</TR>

	<%recnum += 1;
};%>
	<input type="HIDDEN" name="RECNUM" value="<%=recnum%>">

	<%--
	    	 } 
		%> 
--%>
</TABLE>
</DIV>

<A NAME="comentarios">

<CENTER><A HREF="#Miscelaneos">Misceláneos</A> &nbsp; |&nbsp; <A
	HREF="#producto">Producto</A> &nbsp; |&nbsp; <A HREF="#recaudos">Recaudos</A>
&nbsp; |&nbsp;</CENTER>
<%if (msgRT.getE01DPZDXX().equals("1")) {%>
<div id="SecRegistroGarantia" style="display: none">SeccRegistro
Garantia</DIV>
<h4>Registro de Garantia</h4>
<%if (ga0726.getNoResult()) {%>

<TABLE class="tbenter" height="20%">
	<TR>
		<TD ALIGN=CENTER VALIGN=MIDDLE>
		<H4 style="text-align: center">No existen Registro Garantias
		requeridas para esta propuesta. <br>
		</h4>
		</TD>
	</TR>
</TABLE>
<%} else {%>
<TABLE id="mainTable2" class="tableinfo">
	<TD NOWRAP width="100%">

	<TABLE id="headTable2">
		<tr id="trdark">
			<th align=CENTER nowrap width="5%">&nbsp;</th>
			<th align=CENTER nowrap width="5%">
			<div align="center">Cód.</div>
			</th>
			<th align=CENTER nowrap width="10%">
			<div align="center">Descripción</div>
			</th>
			<th align=CENTER nowrap width="15%">
			<div align="center">Fecha</div>
			</th>
			<th align=CENTER nowrap width="20%">
			<div align="right">.</div>
			</th>
			<th align=CENTER nowrap width="20%">
			<div align="right">.</div>
			</th>
			<th align=CENTER nowrap width="10%">
			<div align="right">.</div>
			</th>
		</tr>
	</TABLE>

	<div id="dataDiv2" class="scbarcolor">
	<table id="dataTable2">
		<%boolean firstTime = true;
String gaChk = "";
ga0726.initRow();
int i = 0;

EDP072601Help.initRow();

while (ga0726.getNextRow()) {
	if (ga0726.getFlag().equals("")) {

		EDP072601Help.getNextRow();
		datapro.eibs.beans.EDP072601Message msgL0726 =
			(datapro.eibs.beans.EDP072601Message) EDP072601Help.getRecord();

		if (!msgL0726.getE01DPEFA1().trim().equals("0")) {
			gaChk = "checked";
		}%>
		<TR>
			<TD ALIGN=LEFT NOWRAP width="5%"><input type="checkbox"
				name="COLLCOD2" value="<%=EDP072601Help.getCurrentRow()%>"
				onClick="updGuar(this.value, '<%=msgL0726.getE01DPEPRD().trim()%>', '<%=msgL0726.getE01DPETYP().trim()%>', '<%=msgL0726.getE01DPEREG().trim()%>')"
				<%=gaChk%>
				<%if (msgRT.getE01PLPEST().trim() == ("99")) {
	out.print("disabled");
}%>>
			</TD>
			<TD ALIGN=CENTER NOWRAP width="5%"><a
				href="javascript:radioClick('COLLCOD2',<%=i%>)">
			<DIV NOWRAP><%=ga0726.getRecord(0)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="10%"><a
				href="javascript:radioClick('COLLCOD2',<%=i%>)">
			<DIV NOWRAP><%=ga0726.getRecord(1)%></DIV>
			</a></TD>
			<TD ALIGN=LEFT NOWRAP width="15%"><a
				href="javascript:radioClick('COLLCOD2',<%=i%>)">
			<DIV NOWRAP><%=ga0726.getRecord(2)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="20%"><a
				href="javascript:radioClick('COLLCOD2',<%=i%>)">
			<DIV NOWRAP><%=ga0726.getRecord(3)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="20%"><a
				href="javascript:radioClick('COLLCOD2',<%=i%>)">
			<DIV NOWRAP><%=ga0726.getRecord(4)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="10%"><a
				href="javascript:radioClick('COLLCOD2',<%=i%>)">
			<DIV NOWRAP><%=ga0726.getRecord(5)%></DIV>
			</a></TD>

		</TR>

		<%i++;
}
}%>
	</table>
	</div>
	</TD>
</TABLE>

<SCRIPT language="JavaScript">
     function resizeDoc2() {
      var dataT = document.all["dataTable2"];
       adjustEquTables(headTable2, dataTable2, dataDiv2,1,false);
    }
	resizeDoc2();
	window.onresize=resizeDoc2;
     
</SCRIPT> <%}%>

<div id="SecDocGarantia" style="display: none">Seccion Documentos a
Recaudar por Garantias</DIV>

<A NAME="Documentos Garantías">

<h4>Recaudos por Garantia</h4>
<%if (!ga0726T.getNoResult()) {%>



<TABLE id="mainTable3" class="tableinfo">
	<TD NOWRAP width="100%">
	<TABLE id="headTable3">
		<tr id="trdark">
			<th align=CENTER nowrap width="5%">&nbsp;</th>
			<th align=CENTER nowrap width="5%">
			<div align="center">Cód.</div>
			</th>
			<th align=CENTER nowrap width="10%">
			<div align="center">Descripción</div>
			</th>
			<th align=CENTER nowrap width="5%">
			<div align="center">Cód.</div>
			</th>
			<th align=CENTER nowrap width="15%">
			<div align="center">Fecha</div>
			</th>
			<th align=CENTER nowrap width="20%">
			<div align="right">.</div>
			</th>
			<th align=CENTER nowrap width="20%">
			<div align="right">.</div>
			</th>
		</tr>
	</TABLE>
	<div id="dataDiv3" class="scbarcolor">

	<table id="dataTable3">
		<%boolean firstTime = true;
String gaChkT = "";
ga0726T.initRow();
int i = 0;

while (ga0726T.getNextRow()) {
	if (ga0726T.getFlag().equals("")) {

		EDP072601Help.getNextRow();
		datapro.eibs.beans.EDP072601Message msgL0726T =
			(datapro.eibs.beans.EDP072601Message) EDP072601Help.getRecord();

		if (!msgL0726T.getE01DPZFA1().trim().equals("0")) {
			gaChkT = "checked";
		}%>
		<TR>
			<TD ALIGN=LEFT NOWRAP width="5%"><input type="checkbox"
				name="COLLCOD3" value="<%=EDP072601Help.getCurrentRow()%>"
				onClick="updGuarT(this.value, '<%=msgL0726T.getE01DPZNPR().trim()%>', '<%=msgL0726T.getE01DPZGRT().trim()%>', '<%=msgL0726T.getE01DPZDGA().trim()%>')"
				<%=gaChkT%>
				<%if (msgRT.getE01PLPEST().trim() == ("70")) {
	out.print("disabled");
}%>>
			</TD>
			<TD ALIGN=CENTER NOWRAP width="5%"><a
				href="javascript:radioClick('COLLCOD3',<%=i%>)">
			<DIV NOWRAP><%=ga0726T.getRecord(0)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="10%"><a
				href="javascript:radioClick('COLLCOD3',<%=i%>)">
			<DIV NOWRAP><%=ga0726T.getRecord(1)%></DIV>
			</a></TD>
			<TD ALIGN=LEFT NOWRAP width="15%"><a
				href="javascript:radioClick('COLLCOD3',<%=i%>)">
			<DIV NOWRAP><%=ga0726T.getRecord(2)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="20%"><a
				href="javascript:radioClick('COLLCOD3',<%=i%>)">
			<DIV NOWRAP><%=ga0726T.getRecord(3)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="20%"><a
				href="javascript:radioClick('COLLCOD3',<%=i%>)">
			<DIV NOWRAP><%=ga0726T.getRecord(4)%></DIV>
			</a></TD>
			<TD ALIGN=CENTER NOWRAP width="10%"><a
				href="javascript:radioClick('COLLCOD3',<%=i%>)">
			<DIV NOWRAP><%=ga0726T.getRecord(5)%></DIV>
			</a></TD>

		</TR>

		<%i++;
}
}%>
	</table>
	</div>
	</TD>
</TABLE>


<SCRIPT language="JavaScript">
     function resizeDoc3() {
      var dataT = document.all["dataTable3"];
       adjustEquTables(headTable3, dataTable3, dataDiv3,1,false);
    }
	resizeDoc3();
	window.onresize=resizeDoc3;
     
</SCRIPT> <%}%> <%}%> <br>
<table class="tableinfo">
	<tr id="trdark">
		<td width="20%">
		<div align="right">
		<h4>Ubicación Geográfica
		<h4>
		</div>
		</td>

		<td width="80%" align="left"><input type="text" name="E01PLPUGE"
			size="5" maxlength="4" value="<%=msgRT.getE01PLPUGE().trim()%>"
			<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>>
		<%if (!userPO.getHeader16().equals("5")) {%> <A
			href="javascript:GetCodeDescCNOFC('E01PLPUGE','D01PLPUGE','27')"><IMG
			src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda"
			align="bottom" border="0"> </A> <%}%> <INPUT type="text"
			name="D01PLPUGE" size="35" maxlength="35"
			value="<%=msgRT.getD01DPZMUN().trim()%>" readonly></td>
	</tr>
</table>

<%if ((userPO.getHeader16().equals("9")) || (userPO.getHeader16().equals("5"))) {
	;%>
<table class="tableinfo">

	<tr id="clear">
		<TD ALIGN=CENTER width="50%" class=tdbkg><a
			href="javascript:goDOFA(1)"><b>Análisis FODA</b></a></TD>
		<TD ALIGN=CENTER width="50%" class=tdbkg><a
			href="javascript:goDOFA(2)"><b>Análisis Financiero</b></a></TD>
	</tr>
</table>
<%};%>

<h4>Comentarios Solicitud</h4>
<table class="tableinfo">
	<tr id="trdark">
		<td width="100%" align="center"><TEXTAREA name="E02DPAC99" rows="10"
			cols="80" value="<%=msgRTC.getE02DPAC99().trim()%>"
			onKeyDown="limitText(this.form.E02DPAC99,800);"
			onKeyUp="limitText(this.form.E02DPAC99,800);"
			<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
} else {
	if ((userPO.getHeader16().equals("9"))
		&& (!msgRT.getH01USERID().trim().equals(msgRT.getE01PLPAID().trim()))) {
		out.print("readonly");
	}
}%>>
		</TEXTAREA></td>
	</tr>
</table>

<%--
<%	if (!msgRT.getField("E01DPPCOM").getString().trim().equals("0")){ %>
--%>

<h4>Comentarios</h4>

<table class="tableinfo">
	<tr id="trdark">
		<td width="100%" align="center"><TEXTAREA name="E01DPSCOM"
			maxlength="800" rows="10" cols="80"
			onKeyDown="limitText(this.form.E01DPSCOM,800);"
			onKeyUp="limitText(this.form.E01DPSCOM,800);"
			<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>>
		</TEXTAREA></td>
	</tr>
<td class=TDBKG width="20%">
<div align="center"><a href="javascript:goRetrComm()"><b>Traer
Comentario Anterior</b></a></div>
</td>

</table>

<%--
<% } %>;
--%>

<table cellspacing="0" cellpadding="0" width="100%" border="0">
	<tr>
		<td width="802">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<%if (userPO.getHeader16().equals("9")) {
	;%>

			<tr id="trclear">
				<%if (msgRT.getE01DPUBXX().equals("1")) {%>
				<td width="20%">Monto Aprobado: <INPUT type="text" name="E01PLPAM2"
					size="20" maxlength="20" value="<%=msgRT.getE01PLPAM2().trim()%>"
					<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>
					<%if (msgRT.getE01CRTEST().equals("AA")) {
	out.print("readonly");
}%>
					onkeypress=enterDecimal()></td>
				<%} else {
	;%>
				<%if (msgRT.getE01CRTFG2().equals("1")) {%>
				<td width="20%">Monto Recomendado: <INPUT type="text"
					name="E01PLPAM2" size="20" maxlength="20"
					value="<%=msgRT.getE01PLPAM2().trim()%>"
					<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>
					<%if (msgRT.getE01CRTEST().equals("AA")) {
	out.print("readonly");
}%>
					onkeypress=enterDecimal()></td>
				<%};%>
				<%};%>
				<%if (msgRT.getE01DPUBXX().equals("1")) {%>
				<td width="20%">Fecha Decision: <INPUT type="text" name="E01PLPNFD"
					size="2" maxlength="2" value="<%=msgRT.getE01PLPNFD().trim()%>"
					readonly> <INPUT type="text" name="E01PLPNFM" size="2"
					maxlength="2" value="<%=msgRT.getE01PLPNFM().trim()%>" readonly> <INPUT
					type="text" name="E01PLPNFY" size="2" maxlength="2"
					value="<%=msgRT.getE01PLPNFY().trim()%>" readonly> <%if (!userPO.getHeader16().equals("5")
	&& (!msgRT.getE01CRTEST().equals("AA"))) {%> <A
					href="javascript:DatePicker(document.forms[0].E01PLPNFD,document.forms[0].E01PLPNFM,document.forms[0].E01PLPNFY)"><IMG
					src="<%=request.getContextPath()%>/images/calendar.gif" alt="help"
					align="absmiddle" border="0"> </A> <%};%></td>
				<%};%>
				<%if (msgRT.getE01DPUBXX().equals("1")) {%>
				<td nowrap width="20%" align="left">Numero de Acta: <INPUT
					type="text" name="E01PLPNAC" size="17" maxlength="15"
					value="<%=msgRT.getE01PLPNAC().trim()%>"
					<%if (userPO.getHeader16().equals("5")) {
	out.print("readonly");
}%>
					<%if (msgRT.getE01CRTEST().equals("AA")) {
	out.print("readonly");
}%>>
				</td>
				<%};%>
			</tr>
			<%};%>
		</table>
		</td>
	</tr>
</table>

<CENTER><A HREF="#Miscelaneos">Miscelaneos</A> &nbsp; |&nbsp; <A
	HREF="#producto">Producto</A> &nbsp; |&nbsp; <A HREF="#recaudos">Recaudos</A>
&nbsp; |&nbsp; <A HREF="#comentarios">Comentarios Analista</A> &nbsp;
|&nbsp;</CENTER>


<%if (!(userPO.getHeader16().equals("1"))) {%>

<h4>Seleccione Próxima Actividad:</h4>

<TABLE id="tbenter" border="0" cellspacing="1" cellpadding="0">
	<TR id="trdark">
		<td width="30%"><SELECT name="available" size="5"
			<%if (userPO.getHeader16().equals("X")) {
	out.print("DISABLED");
}%>>
			<%optLP4.initRow();
while (optLP4.getNextRow()) {
	if (optLP4.getFlag().equals("")) {
		out.println(optLP4.getRecord());
	}
}%>
		</SELECT></td>
		<td width="10%"><INPUT type="button" value="Seleccionar"
			onclick="moveOver();"
			<%if (userPO.getHeader16().equals("X") || userPO.getHeader16().equals("5")) {
	out.print("DISABLED");
}%>>
		</td>
		<td width="60%" align="left" valign="middle"><INPUT type="text"
			name="E01DPPPXX" size="5" maxlength="5"
			value="<%=msgRT.getE01DPPPXX().trim()%>" readonly> <INPUT type="text"
			name="E01DPPDXX" size="35" maxlength="35"
			value="<%=msgRT.getE01DPPDXX().trim()%>" readonly></td>
	</tr>
</TABLE>
<%};%>

<div align="center"><input id="EIBSBTN" type="button" name="Submit"
	value="Siguiente Actividad"
	onclick="goControlDoc('<%=userPO.getHeader16()%>')"
	<%if (userPO.getHeader16().equals("5")) {
	out.print("DISABLED");
}%>>
<%if (!userPO.getPurpose().equals("NEW")
	&& !(userPO.getHeader16().equals("1"))) {%> <INPUT id="EIBSBTN"
	type="button" name="Submit0" value="Salvar"
	onclick="goConfirm('<%=userPO.getHeader16()%>','<%=userPO.getHeader23()%>','<%=userPO.getHeader21()%>')"
	<%if (userPO.getHeader16().equals("5")) {
	out.print("DISABLED");
}%>>
<%};%> <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar"
	onclick="goCancel('<%=userPO.getHeader9().trim()%>')"></div>

<SCRIPT language="JavaScript">

     function resizeDoc() {
      var actvTb = document.forms[0].CCOD.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
      }
//      	showCollItemsRD("COLLCODRD");
 showChecked("COLLCODRD");

	</SCRIPT></form>
</body>
</html>
