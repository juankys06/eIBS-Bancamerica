<%@ page contentType="application/x-javascript" %>

var menuImgRef;
var menuRef;
var hPopupRef;
var mnuHelpRef;

// Help Menu Global variable for english

var accountHelp=1;
var ledgerHelp=2;
var branchHelp=3;
var custdescidHelp=4;
var currencyHelp=5;
var conceptHelp=6;
var cnofHelp=7;
var costcenterHelp=8;
var accholdersHelp=9;
var codeDescCNOFC=10;
var causeHelp=11;
var conceptTransacHelp=12;
var staticFactHelp=13;
var staticFrecHelp=14;
var documentsFrecuencyHelp = 15;
var documentsTypeHelp = 16;
var documentsFeeCharges = 17;
var documentsFrecCharges = 18;
var concepttypeHelp = 19;
var lnreferHelp=20;
var openingWireHelp=21

//var fieldAux1;
//var fieldAux2;
var fieldBank;
var fieldCCY;
var fieldOPCode;
var optHelp;
var menuactive = false;
var animactive = false;
var ontime = "";
var outtime = "";
var outtime2 = "";
var curlen = 0;
var popupStatus = "open"


//Help menu definitions
var language = "s/";
var prefix = "<%=request.getContextPath()%>/pages/";

function IncludeJavaScript(jsFile) {
	document.write('<' + 'script');
    document.write(' language="javascript"');
    document.write(' type="text/javascript"');
    document.write(' src="' + prefix + language + 'javascripts/' + jsFile + '">');
    document.write('</' + 'script' + '>');
}

IncludeJavaScript('graphical.jsp');

function getHelp() {

 switch (optHelp) {
   case 1 :
      GetAccount(fieldName,fieldBank,fieldOPCode,'');
      break;
   case 2 :
      GetLedger(fieldName,fieldBank,fieldCCY,'',fieldDesc);
      break;
   case 3 :
      GetBranch(fieldName,fieldBank);
      break;
   case 4 :
      GetCustomerDescId(fieldName,fieldAux1,fieldAux2);
      break;
   case 5 :
      GetCurrency(fieldName,fieldBank);
      break;
   case 6 :
      GetConcept(fieldName,fieldBank,fieldOPCode,fieldAux1,fieldAux2);
      break;
   case 7 :
      GetCodeCNOFC(fieldName,fieldOPCode);
      break;
   case 8 :
      GetCostCenter(fieldName,fieldBank);
      break;
   case 9 :
      GetAccountHolders(fieldName);
      break;
   case 10 :
      GetCodeDescCNOFC(fieldName, fieldAux1, fieldOPCode);
      break;
   case 11 :
      GetCasualTable(fieldName,fieldDesc);
      break;
   case 12 :
      //GetConceptTransac(fieldCode1,fieldBank,fieldOPCode,fieldCode2,fieldCode3,fieldCode4,fieldCode5,fieldCode6);
      GetConceptTr(fieldName,fieldBank,fieldOPCode,fieldAux1,fieldAux2);
      break;
   case 13 :
      GetCode(fieldName,'STATIC_inv_factor.jsp');
      break;
   case 14 :
      GetCode(fieldName,'STATIC_inv_frecuency.jsp');
      break;
   case 15 :
      GetCode(fieldName,'STATIC_doc_frecuency.jsp');
      break;
   case 16 :
      GetCode(fieldName,'STATIC_doc_type.jsp');
      break;
   case 17 :
      GetCode(fieldName,'STATIC_ln_tables_fee_charges.jsp');
      break;
   case 18 :
      GetCode(fieldName,'STATIC_ln_tables_frec_charges.jsp');
      break;
   case 19 :
      GetCode(fieldName,'STATIC_concepts_type.jsp');
      break;
   case 20 :
   	  GetLNReference(fieldName,fieldBank,fieldOPCode,'',fieldAux1);
   	  break;
    case 21 :
      if (fieldAux2 == "03") {
      		GetWireOpening(fieldName,fieldAux1);
      } else {
      		GetAccount(fieldName,fieldBank,fieldOPCode,'');
      }
      break;
 }
}

function setTime(maxlen) {
  clearTimeout(outtime2);
  ontime=setInterval("AnimationMenu("+maxlen+")",1);
}

function startMove(){
  if (curlen == 0) {
    var maxlen = menuRef.clientWidth;
    if (menuactive) {
    	setVisibility(menuRef, "hidden");
     	outtime2 = setTimeout("setTime("+maxlen+")",1000);
   }else {
      setTime(maxlen);
   }
 }
}

function AnimationMenu(maxlen){
  var x = menuImgRef.offsetLeft;
  var x2 = x + menuImgRef.clientWidth + 2;
  var w = menuImgRef.clientWidth + 2 + menuRef.clientWidth;

 animactive = true;
 curlen = curlen + 10;
 clearTimeout(outtime);
 if (curlen < maxlen) {
  x = (!menuactive) ? x - 10 : x + 10;
  x2 = (!menuactive) ? x2 - 10 : x2 + 10;
  menuImgRef.style.left = x + "px";
  checkCombo(x,menuImgRef.offsetTop,w,menuRef.clientHeight);
  }
 else {
   clearInterval(ontime);
   curlen = 0;
   var rest= (maxlen % 10) + 2;
   if (menuactive == true) {
    x += rest;
    menuImgRef.style.left= x + "px";
    menuactive = false;
    animactive = false;
    checkCombo(x,menuImgRef.offsetTop,menuImgRef.clientWidth,menuImgRef.clientHeight);
    }
   else{
    x -= rest;
    x2 -= rest;
    menuImgRef.style.left= x + "px";
    menuRef.style.left= x2 + "px";

    checkCombo(x,menuImgRef.offsetTop,w,menuRef.clientHeight);
	setVisibility(menuRef, "visible");

    menuactive = true;
    outtime = setTimeout("startMove()",20000);}
 }

}


function MoveMenu(){
 var obj = document.body;
 var y = obj.scrollTop + 2;
 var x = obj.clientWidth - menuImgRef.clientWidth - 2 + obj.scrollLeft;
 var x2 = 0;

if (!animactive){
   menuImgRef.style.top=y + "px";
   menuRef.style.top= y + "px";
   menuImgRef.style.left= x + "px";
   checkCombo(x,y,menuImgRef.clientWidth,menuImgRef.clientHeight);
   menuactive = false;
  }
 else if (curlen ==0) {
   x -= menuRef.clientWidth + 2;
   x2 += x + menuImgRef.clientWidth + 2;
   menuImgRef.style.top= y  + "px";
   menuRef.style.top= y  + "px";
   menuImgRef.style.left=x + "px";
   menuRef.style.left=x2 + "px";
   checkCombo(x,y,menuImgRef.clientWidth + menuRef.clientWidth + 2,menuRef.clientHeight);
   }

}

function setCapture(element) {
	if (!element.setCapture) {
	    var capture = ["click", "mousedown", "mouseup", "mousemove", "mouseover", "mouseout" ];

	    element.setCapture = function(){
	        var self = this;
	        var flag = false;
	        this._capture = function(e){
	            if (flag) {return}
	            flag = true;
	            var event = document.createEvent("MouseEvents");
	            event.initMouseEvent(e.type,
	                e.bubbles, e.cancelable, e.view, e.detail,
	                e.screenX, e.screenY, e.clientX, e.clientY,
	                e.ctrlKey, e.altKey, e.shiftKey, e.metaKey,
	                e.button, e.relatedTarget);
	            self.dispatchEvent(event);
	            flag = false;
	        };
	        for (var i=0; i<capture.length; i++) {
	            window.addEventListener(capture[i], this._capture, true);
	        }
	    };
	}
	element.setCapture();
}

function releaseCapture(element) {
	if (!element.releaseCapture) {
	    var capture = ["click", "mousedown", "mouseup", "mousemove", "mouseover", "mouseout" ];

	    element.releaseCapture = function(){
	        for (var i=0; i<capture.length; i++) {
	            window.removeEventListener(capture[i], this._capture, true);
	        }
	        this._capture = null;
	    };
	}
	element.releaseCapture();
}

function switchMenu(evt) {
	var elem = getEventElement(evt);

  if (popupStatus=="open"){
    popupStatus="close";
    setCapture(hPopupRef);
    //hPopupRef.setCapture();
   }
   if (elem.className=="menuItem") {
      elem.className="highlightItem";
   } else if (elem.className=="highlightItem") {
      elem.className="menuItem";
   }
}

function doCut() {
 var el = document.forms[0][fieldName];
 if (doCopy()) { el.value = "";}
}

function doCopy() {
 var el = document.forms[0][fieldName];
 var bResult = window.clipboardData.setData("Text",el.value);
 return bResult;
}

function doPaste() {
 var el = document.forms[0][fieldName];
 el.value = window.clipboardData.getData("Text");
}

function doDelete() {
 var el = document.forms[0][fieldName];
 el.value = "";
}

function hideMenu() {
 hPopupRef.style.display="none";
}

function clickMenu(evt) {

   hPopupRef.style.display="none";
   //hPopupRef.releaseCapture();
   releaseCapture(hPopupRef);

   el=getEventElement(evt);
   document.forms[0][fieldName].style.cursor="auto";
   if (el.id=="mnuHelp") {
       getHelp();
   } else if (el.id=="mnuCut") {
       doCut();
   } else if (el.id=="mnuCopy") {
       doCopy();
   } else if (el.id=="mnuPaste") {
       doPaste();
   } else if (el.id=="mnuDelete") {
       doDelete();
   }
}

function builtHPopUp() {
var styleHtml ="<STYLE>"+
   	      ".menuItem {font-family:sans-serif;font-size:8pt;width:80;padding:2;padding-left:20;background-Color:menu;color:black;cursor:default}"+
   	      ".highlightItem {font-family:sans-serif;font-size:8pt;width:80;padding:2;padding-left:20;background-Color:highlight;color:white;cursor:default}"+
   	      " </STYLE>";
document.write(styleHtml);

var divHtml = "<div id=\"hPopup\" style=\"position:absolute; display:none; left:25px; top:-50px; z-index:3; padding:1; background-Color:menu; border-width:thin; border-style:outset; cursor:default\">"+
              "<div class=\"menuItem\" id=mnuHelp style=\"border-bottom-width:thin; border-bottom-style:groove;\">Help</div>"+
	      "<div class=\"menuItem\" id=mnuCut>Cut</div>"+
	      "<div class=\"menuItem\" id=mnuCopy>Copy</div>"+
	      "<div class=\"menuItem\" id=mnuPaste>Paste</div>"+
	      "<div class=\"menuItem\" id=mnuDelete>Delete</div>"+
  	      "</div>";
document.write(divHtml);

var switchHtml ="<SCRIPT language=\"JavaScript\">"+
	       			"document.getElementById(\"hPopup\").onmousedown = clickMenu;"+
	       			"document.getElementById(\"hPopup\").onmouseover = switchMenu;"+
	       			"document.getElementById(\"hPopup\").onmouseout = switchMenu;"+
	       		"</SCRIPT>";
document.write(switchHtml);

hPopupRef=document.getElementById("hPopup");
mnuHelpRef=document.getElementById("mnuHelpRef");
}

function builtNewMenu(options) {
var divHtml = "<div id=\"Menu\" class=\"hiddenDiv\" onclick=\"cancelBub()\">"+
              "<table class=\"optMenuTB\">"+
    	      "<td align=\"left\" nowrap style=\"padding-right:9.5pt;\">"+ options +
    	      "</td>"+
              "</table>"+
  	      "</div><BR>";
var divImg = "<div id=\"MenuImg\" class=\"hiddenDivImg\" >"+
             "<IMG SRC = '<%=request.getContextPath()%>/images/s/options.gif' onmouseover=\"startMove()\">"+
  	     "</div>";

document.write(divImg);
document.write(divHtml);

var hideHtml = "<SCRIPT language=\"JavaScript\">"+
	       "document.onclick= closeMenu;"+
	       "</SCRIPT>";
document.write(hideHtml);

 menuImgRef = document.getElementById("MenuImg");
 menuRef = document.getElementById("Menu");
}

function showPopupHelp(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");

     var y=evt.clientY + document.body.scrollTop - 1;
     var x=evt.clientX + document.body.scrollLeft - 1;
     var bodyW = document.body.clientWidth;
     var bodyH = document.body.clientHeight;
     var dif=0;
     hPopupRef.style.display="";
     if (bodyH < (evt.clientY + hPopupRef.offsetHeight)) {
      dif = (evt.clientY + hPopupRef.offsetHeight) - bodyH;
      y= y - dif - 5;
     }
     if (bodyW < (evt.clientX + hPopupRef.offsetWidth)) {
      dif = (evt.clientX + hPopupRef.offsetWidth) - bodyW;
      x= x - dif - 5;
     }
     if(evt.srcElement){
	     evt.returnValue = false;
     } else {
	     evt.preventDefault();
     }
     popupStatus = "open"
     x= x - 3;
     y= y - 3;
     hPopupRef.style.top=y + "px";
     hPopupRef.style.left=x + "px";
}

function closeMenu(){
 if (menuactive == true) startMove();
}

function initMenu(){

 	var gentime = "";

	menuImgRef.style.visibility="visible";
	gentime = setInterval('MoveMenu()',10);

	applyTrans(menuRef);
}

function init(opt,name,bank,ccy,fieldname1,fieldname2,opcode) {
 fieldName = name;
 fieldBank = bank;
 fieldCCY = ccy;
 optHelp = opt;
 fieldAux1 = fieldname1;
 fieldAux2 = fieldname2;
 fieldOPCode = opcode;
}

function getPdfForms(url) {
	CenterNamedWindow(
		url,
		'pdf',
		800,
		600,
		2);
}

function confirmDelete(url) {
	if (confirm("Esta usted seguro que desea eliminar este registro?")) {
		window.location.href = url;
	} 
}	

//Menu Options


// Corporative Customer option

var noclient_opt =
//   "<A HREF= javascript:getIF01Forms('<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM030?SCREEN=1')>Formularios (IF01)</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Corporative Customer option

var client_corp_opt =
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=11>Información Básica</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=31>Códigos de Clasificación</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=33>Direcciones de Correo</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=35>Instrucciones Especiales</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=37>Comunicaciones</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=39>Referencias Bancarias</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=41>Referencias Comerciales</A><BR>" +
//   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=43>Referencias Personales</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=13>Accionistas</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=15>Junta Directiva</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=45>Representantes Legales</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=17>Activos</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=19>Pasivos</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=21>Balance</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=C>Documentación </A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01&APP_CODE=00>Formularios (Word)</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01&APP_CODE=00')>Formularios (PDF)</A><BR>" +
//  "<A HREF= javascript:getIF01Forms('<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM030?SCREEN=1')>Formularios (IF01)</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=47>Mercadeo</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=7>Mensajes del Cliente</A><BR> " +
   "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Inquiry Corporative Customer option

var client_inq_corp_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=11>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=31>Códigos de Clasificación</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=33>Direcciones de Correo</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=35>Instrucciones Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=37>Comunicaciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=39>Referencias Bancarias</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=41>Referencias Comerciales</A><BR>" +
//  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=43>Referencias Personales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400&TYPE=CUST>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=13>Accionistas</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=15>Junta Directiva</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=45>Representantes Legales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=17>Activos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=19>Pasivos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=21>Balance</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=C>Documentación </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=47>Mercadeo</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=9>Mensajes del Cliente</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Personal Customer option

var client_personal_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=1>Información Básica</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=31>Códigos de Clasificación</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=3>Beneficiarios</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=33>Direcciones de Correo</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=35>Instrucciones Especiales</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=37>Comunicaciones</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=5>Datos de Empleo</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=39>Referencias Bancarias</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=41>Referencias Comerciales</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=43>Referencias Personales</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=45>Representates Legales</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=C>Documentación </A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01&APP_CODE=00>Formularios (Word)</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01&APP_CODE=00')>Formularios (PDF)</A><BR>" +
//  "<A HREF= javascript:getIF01Forms('<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM030?SCREEN=1')>Formularios (IF01)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=47>Mercadeo</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=7>Mensajes del Cliente</A><BR> " + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0095?SCREEN=1>Afiliaci&oacute;n al IVR</A><BR> " +   
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Inquiry Personal Customer option

var client_inq_personal_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=1>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=31>Códigos de Clasificación</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=3>Beneficiarios</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=33>Direcciones de Correo</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=35>Instrucciones Especiales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=37>Comunicaciones</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=5>Datos de Empleo</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=39>Referencias Bancarias</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=41>Referencias Comerciales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=43>Referencias Personales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=45>Representates Legales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400&TYPE=CUST>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=C>Documentación </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080?SCREEN=47>Mercadeo</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=9>Mensajes del Cliente</A><BR> " +	
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// CD maintenance

var cd_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=33>Instrucciones de Renovación/Pago</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=21>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=7>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=17>Beneficiarios</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=15>Instrucciones Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=49>Entradas Contables</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// CD Transactions

var cd_t_m_opt =
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=5>Transacciones</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=49>Transacciones Adicionales</A><BR> " +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// CD inquiry

var cd_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=13>Saldos </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=41>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=27>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=29>Titulares</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=31>Instrucciones Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0300?SCREEN=6>Analisis de Intereses</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF040?SCREEN=1>Promedios</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0300?SCREEN=200>Estado de Cuentas</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=43>Producto</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=49>Tabla de Tasas</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=44>Beneficiarios</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=45>Calculadora</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=46>Opr.Garantizadas</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0130I?SCREEN=48>Relaciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// Retail Accounts maintenance

var rt_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=5>Sobregiros</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=7>Cliente/Linea de Credito</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=11>Control de Lavado de Dinero</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=15>Cambio de Status de la Cuenta</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=1&Type=FI>Reglas de Firmas </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=13>Codigos Especiales </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=17>Titulares</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=23>Beneficiarios</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=21>Instrucciones Especiales</A><BR> "+
  "<!--A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0090?SCREEN=400&Type=D&menu=T></A><BR-->" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=47>Representantes Legales</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=1>Mensajes del Cliente</A><BR> " +	 
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Retail Accounts condition's document

var rt_m_opt_f =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var rt_i_opt_f =
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=32>Informacion Basica</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var sv_m_opt_f =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var sv_i_opt_f =
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=32>Informacion Basica</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Retail Accounts condition's document

var rt_m_opt_c =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var sv_m_opt_c =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Retail Account inquiry

var rt_i_opt =
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=31>Saldos</A><BR>"+
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=32>Informacion Basica</A><BR>" +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=33>Control de Lavado de Dinero</A><BR>" +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF040?SCREEN=1>Promedios</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=1&Type=FI>Reglas de Firmas </A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=35>Codigos Especiales </A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=50>Representantes Legales</A><BR> " +
"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=38>Titulares</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=39>Beneficiarios</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=37>Instrucciones Especiales</A><BR> "+
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF030?SCREEN=200>Estado de Cuentas</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0395?SCREEN=1>Retenciones y Diferidos</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0405?SCREEN=1>Suspenciones de Pagos</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0565?SCREEN=200>Chequeras</A><BR> " +
"<!--A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060I?SCREEN=1&Type=D>Tarjetas de Debito</A><BR-->" +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0936?SCREEN=1>Cuentas Pendientes de Cobro</A><BR>"+
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=42>Balance Diario</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=43>Operaciones Garantizadas</A><BR> "+
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=44>Garantías Asignadas</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=40>Producto</A><BR> " +
"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0205I?SCREEN=700>Cargos por Servicios y Tasas</A><BR> " +
"<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=3>Mensajes del Cliente</A><BR> " +	
"<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Savings Accounts maintenance

var sv_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=27>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=5>Sobregiros</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=7>Cliente/Linea de Credito</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=11>Control de Lavado de Dinero</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=15>Cambio de Status de la Cuenta</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=1&Type=FI>Reglas de Firmas </A><BR> " +  
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=13>Codigos Especiales </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=17>Titulares</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=23>Beneficiarios</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=21>Instrucciones Especiales</A><BR> "+
  "<!--A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060?SCREEN=600&Type=D>Tarjetas de Debito</A><BR-->" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=47>Representantes Legales</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=1>Mensajes del Cliente</A><BR> " +	 
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Savings Documents Signers

var sv_m_opt_d =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDD0000?SCREEN=27>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Savings Account inquiry

var sv_i_opt =
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=31>Saldos</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=32>Informacion Basica</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=33>Control de Lavado de Dinero</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF040?SCREEN=1>Promedios</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500?SCREEN=1&Type=FI>Reglas de Firmas </A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=35>Codigos Especiales </A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=50>Representantes Legales</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=38>Titulares</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=39>Beneficiarios</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=37>Instrucciones Especiales</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF030?SCREEN=200>Estado de Cuentas</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0395?SCREEN=1>Retenciones y Diferidos</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0405?SCREEN=1>Suspenciones de Pagos</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0990?SCREEN=1>Lineas de Impresión de Libretas de Ahorro</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0936?SCREEN=1>Cuentas Pendientes de Cobro</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=42>Balance Diario</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=43>Operaciones Garantizadas</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=40>Producto</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +  
  "<!--A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060I?SCREEN=1&Type=D>Tarjetas de Debito</A><BR-->" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.params.JSESD0205I?SCREEN=700>Cargos por Servicios y Tasas</A><BR> " +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=3>Mensajes del Cliente</A><BR> " +	 
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// Loans maintenance

var ln_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=3>Información Básica </A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=8&Type=M>Plan de Pagos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=15>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=7>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=11>Instrucciones Especiales<BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=33>Suspensión Débito Autom.</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=27>Entradas Contables</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// Loans new

var ln_n_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=3>Información Básica </A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=25>Transacciones </A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=15>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=7>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=11>Instrucciones Especiales<BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=33>Suspensión Débito Autom.</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=1&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

  // Loans transactions

var ln_t_m_opt =
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0152?SCREEN=5>Transacciones</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0152?SCREEN=27>Transacciones Adicionales</A><BR> " +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Loans inquiry

var ln_i_1_opt  =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=1>Saldos </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=2>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=3>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=4>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=5>Instrucciones Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=6>Analisis de Intereses</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=33>Suspensión Débito Autom.</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF040?SCREEN=1>Promedios</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=200>Estado de Cuentas</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0816?SCREEN=1>Lista de Documentos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0290?SCREEN=1>Calculadora</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=10>Producto</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=13>Tabla de Cargos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=12>Garantías Asignadas</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0305?SCREEN=1>Deducciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0305?SCREEN=3>Relaciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


var ln_i_2_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=1>Saldos </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=2>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=3>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0150?SCREEN=27&PURPOSE=READONLY>Entradas Contables</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=4>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=5>Instrucciones Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=6>Analisis de Intereses</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=33>Suspensión Débito Autom.</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF040?SCREEN=1>Promedios</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=200>Estado de Cuentas</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=8&Type=I>Plan de Pagos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0290?SCREEN=1>Calculadora</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=10>Producto</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=13>Tabla de Cargos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=12>Garantías Asignadas</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0305?SCREEN=1>Deducciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0305?SCREEN=3>Relaciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

 // ejecutivos

var ej_option =
  "<A HREF= \"javascript:callPosition();\">Posicion del Cliente</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";
 // Credit Proposal

var pc_option =
//  "<A HREF= \"javascript:callCust();\">Datos Basicos Cliente</A><BR>" +
  "<A HREF= \"javascript:callInfF();\">Informacion Financiera</A><BR>" +
//  "<A HREF= \"javascript:callPosition();\">Posicion del Cliente</A><BR>" +
  "<A HREF= \"javascript:callCupo();\">Cupo Lineas de Credito</A><BR>" +
//  "<A HREF= \"javascript:callTitul();\">Titulares</A><BR>" +
  "<A HREF= \"javascript:callGuard();\">Garantías & Avales</A><BR>" +
//  "<A HREF= \"javascript:callForex();\">Transacciones Compra/Venta</A><BR>" +
  "<A HREF= \"javascript:callSegP();\">Seguimiento Propuesta</A><BR>" +
  "<A HREF= \"javascript:callImpP();\">Imprimir Propuesta</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:callDebtOthers();\">Endeudamiento con Otras Entidades</A><BR>" +
  "<A HREF= \"javascript:callCreditScor();\">Asignación Puntaje Credit Scoring</A><BR>" +
  "<A HREF= \"javascript:callAmountCreditScor();\">Montos Evaluación Credit Scoring</A><BR>" +
  "<A HREF= \"javascript:callSummaryCreditScor();\">Resumen Credit Scoring</A><BR>" +
//  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
//  "<A HREF= \"javascript:callWarrGen();\">Garantías Generales Propuestas</A><BR>" +
//  "<A HREF= \"javascript:callWarrEsp();\">Garantías Específicas Propuestas</A><BR>" +
//  "<A HREF= \"javascript:callRiskRecom(3);\">Descripción del Cliente</A><BR>" +
//  "<A HREF= \"javascript:callRiskRecom(2);\">Analisis Financiero</A><BR>" +
//  "<A HREF= \"javascript:callRiskRecom(4);\">Recomendaciones</A><BR>" +
//  "<A HREF= \"javascript:callCualAnalysis();\">Evaluación Analisis Cualitativo</A><BR>" +
//  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
//  "<A HREF= \"javascript:callAccumulates(1);\">Depósitos en Otras Instituciones</A><BR>" +
//  "<A HREF= \"javascript:callAccumulates(2);\">Saldos Finales en Otras Instituciones</A><BR>" +
//  "<A HREF= \"javascript:callAccumulates(3);\">Depósitos en Otras Instituciones-Grupo</A><BR>" +
//  "<A HREF= \"javascript:callAccumulates(4);\">Saldos Finales en Otras Instituciones-Grupo</A><BR>" +
//  "<A HREF= \"javascript:callPosCons();\">Posición Consolidada Riesgo del Grupo</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


 // Credit Proposal Header

var pc_optionHeader =

//  "<A HREF= \"javascript:callCust();\">Datos Basicos Cliente</A><BR>" +
  "<A HREF= \"javascript:callInfF();\">Informacion Financiera</A><BR>" +
//  "<A HREF= \"javascript:callPosition();\">Posicion del Cliente</A><BR>" +
//  "<A HREF= \"javascript:callGroup();\">Posicion del Grupo</A><BR>" +
  "<A HREF= \"javascript:callCupo();\">Cupo Lineas de Credito</A><BR>" +
  "<A HREF= \"javascript:callGuard();\">Garantías & Avales</A><BR>" +
  "<A HREF= \"javascript:callDocum();\">Documentación</A><BR>" +
  "<A HREF= \"javascript:callSegP();\">Seguimiento Propuesta</A><BR>" +
  "<A HREF= \"javascript:callImpP();\">Imprimir Propuesta</A><BR>" +
  "<A HREF= \"javascript:callPayS();\">Plan de Pagos</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:callDebtOthers();\">Endeudamiento con Otras Entidades</A><BR>" +
  "<A HREF= \"javascript:callCreditScor();\">Asignacion Puntaje Credit Scoring</A><BR>" +
  "<A HREF= \"javascript:callAmountCreditScor();\">Montos Evaluación Credit Scoring</A><BR>" +
  "<A HREF= \"javascript:callSummaryCreditScor();\">Resumen Credit Scoring</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

 // Credit Proposal Garantias/Avales

var pc_optionGaran =
  "<A HREF= \"javascript:callInfF();\">Informacion Financiera Aval</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var pc_limits =
  "<A HREF= \"javascript:callLimits();\">Límites de Exposición de Riesgo</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var pc_docs =
  "<A HREF= \"javascript:callDocs();\">Control Documentos por Garantía</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Credit cards maintenance

var cc_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060?SCREEN=5>Tarjeta Primaria</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010?SCREEN=7>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010?SCREEN=9>Instrucciones Especiales</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Credit cards inquiry

var cc_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010I?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010I?SCREEN=7>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010I?SCREEN=9>Instrucciones Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060I?SCREEN=5>Tarjeta Primaria</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060I?SCREEN=1>Tarjetas Adicionales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Debit cards inquiry

var dc_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060I?SCREEN=4&Type=D>Información Básica</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060I?SCREEN=1>Tarjetas Adicionales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080I?SCREEN=800>Cuentas Asociadas</A><BR>" +
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Collections inquiry

var coll_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSEDC0100?SCREEN=1>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSEDC0100?SCREEN=3>Códigos de Clasificación</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSEDC0100?SCREEN=7>Instrucciones Especiales</A><BR> "  +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSESD0817C?SCREEN=1>Estado de Cuentas</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSEDC0100?SCREEN=9>Productos</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSEDC0100?SCREEN=11>Tabla de Cargos</A><BR> "  +
   "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
   "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Letter of Credit inquiry

var lc_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=1>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=3>Códigos de Clasificación</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=7>Instrucciones Especiales</A><BR> "  +
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSESD0817?SCREEN=1>Estado de Cuentas</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=9>Productos</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=11>Tabla de Cargos</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=13>Garantias</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0455?SCREEN=1>Lista de Documentos</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=39C>Montos Adicionales Cubiertos (39C)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=41D>Credito Disponible Con (41D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=42C>Giros A.. (42C)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=42D>Banco Girado (42D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=42M>Detalle de Pago Mixto (42M)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=42P>Detalle de Pago Diferido (42P)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=44D>Período de Embarque (44D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=45A>Descripción de Mercancía (45A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=46A>Documentos Requeridos (46A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=47A>Condiciones Adicionales (47A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=48>Período para Presentación (48)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=71B>Cargos (71B)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=72>Información de Banco a Banco (72)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=77A>Narrativa para el Banco Reembolsador (77A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=78>Instrucciones para el Banco Pagador (78)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSELC0450?SCREEN=52&fcode=79&CODE=79>Narrativa (79)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";
  
// Letter of Credit Transfers 
var lc_transfer =   
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=106>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=108>Comisiones</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var lc_approval_transfer =   
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5107>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5108>Comisiones</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=1>Aprobar</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=2>Eliminar</A><BR>"+
  "<A HREF=javascript:void(CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8',450,350,2)) >Mensaje SWIFT</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Letter of Credit Opening (Apertura)

var lc_opening =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=5>Datos del Credito</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=7>Comisiones</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=101>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=103>Instruccciones Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=601>Documentos Requeridos</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=39C>Montos Adicionales Cubiertos (39C)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=41D>Credito Disponible Con (41D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=42C>Giros A.. (42C)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=42D>Banco Girado (42D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=42M>Detalle de Pago Mixto (42M)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=42P>Detalle de Pago Diferido (42P)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=44D>Período de Embarque (44D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=45A>Descripción de Mercancía (45A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=46A>Documentos Requeridos (46A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=47A>Condiciones Adicionales (47A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=48>Período para Presentación (48)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=71B>Cargos (71B)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=72>Información de Banco a Banco (72)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=77A>Narrativa para el Banco Reembolsador (77A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0500?SCREEN=50&CODE=78>Instrucciones para el Banco Pagador (78)</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Stand By Opening (Apertura)

var sb_opening =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=5>Datos del Credito</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=7>Comisiones</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=101>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=103>Instruccciones Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=50&CODE=45A>Descripción de Mercancía (45A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=50&CODE=72>Información de Banco a Banco (72)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0400?SCREEN=50&CODE=77C>Detalles De La Garantia  (77C)</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Letter of Credit Approbal

var lc_approbal_detail =
  "<A HREF= javascript:goDetail('6');>Información Básica</A><BR>"+
  "<A HREF= javascript:goDetail('8');>Datos del Credito</A><BR>"+
  "<A HREF= javascript:goDetail('7');>Comisiones</A><BR>"+
  "<A HREF= javascript:goDetail('101');>Códigos Especiales</A><BR>"+
  "<A HREF= javascript:goDetail('103');>Instruccciones Especiales</A><BR>"+
  "<A HREF= javascript:goDetail('28');>Montos Adicionales Cubiertos (39C)</A><BR>"+
  "<A HREF= javascript:goDetail('30');>Giros A.. (42C)</A><BR>"+
  "<A HREF= javascript:goDetail('32');>Detalle de Pago Mixto (42M)</A><BR>"+
  "<A HREF= javascript:goDetail('34');>Detalle de Pago Diferido (42P)</A><BR>"+
  "<A HREF= javascript:goDetail('10');>Período de Embarque (44D)</A><BR>"+
  "<A HREF= javascript:goDetail('12');>Descripción de Mercancía (45A)</A><BR>"+
  "<A HREF= javascript:goDetail('14');>Documentos Requeridos (46A)</A><BR>"+
  "<A HREF= javascript:goDetail('16');>Condiciones Adicionales (47A)</A><BR>"+
  "<A HREF= javascript:goDetail('18');>Período para Presentación (48)</A><BR>"+
  "<A HREF= javascript:goDetail('20');>Cargos (71B)</A><BR>"+
  "<A HREF= javascript:goDetail('22');>Información de Banco a Banco (72)</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= javascript:goMsgSwift();>Mensaje SWIFT</A><BR>"+
  "<A HREF=javascript:goAction('A');>Aprobar</A><BR>"+
  "<A HREF=javascript:setReason('R','reason');>Rechazar</A><BR>"+
  "<A HREF=javascript:goAction('D');>Eliminar</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

 var lc_maint_detail =
  "<A HREF= javascript:goDetail('206');>Información Básica</A><BR>"+
  "<A HREF= javascript:goDetail('208');>Datos del Credito</A><BR>"+
  "<A HREF= javascript:goDetail('207');>Comisiones</A><BR>"+
  "<A HREF= javascript:goDetail('20101');>Códigos Especiales</A><BR>"+
  "<A HREF= javascript:goDetail('2028');>Montos Adicionales Cubiertos (39C)</A><BR>"+
  "<A HREF= javascript:goDetail('2010');>Período de Embarque (44D)</A><BR>"+
  "<A HREF= javascript:goDetail('2022');>Información de Banco a Banco (72)</A><BR>"+
  "<A HREF= javascript:goDetail('2026');>Narrativa (79)</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF=javascript:goAction('A');>Aprobar</A><BR>"+
  "<A HREF=javascript:goAction('D');>Eliminar</A><BR>"+
  "<A HREF= javascript:goMsgSwift();>Mensaje SWIFT</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// LETTER OF CREDIT APPROVAL

		// COMERCIAL NEW
		var lc_apr_cc_new =
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5001>Información Básica</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5003>Datos del Credito</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5005>Comisiones</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5006&fcode=ELC0500>Códigos Especiales</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5004&fcode=ELC0500>Instruccciones Especiales</A><BR>"+
  		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5002>Documentos Requeridos</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=39C>Montos Adicionales Cubiertos (39C)</A><BR>" +
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=41D>Credito Disponible Con (41D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=42C>Giros A.. (42C)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=42D>Banco Girado (42D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=42M>Detalle de Pago Mixto (42M)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=42P>Detalle de Pago Diferido (42P)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=44D>Período de Embarque (44D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=45A>Descripción de Mercancía (45A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=46A>Documentos Requeridos (46A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=47A>Condiciones Adicionales (47A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=48>Período para Presentación (48)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=71B>Cargos (71B)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=72>Información de Banco a Banco (72)</A><BR>"+
  		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=50&fcode=78>Instrucciones para el Banco Pagador (78)</A><BR>"+
		"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=1>Aprobar</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=2>Eliminar</A><BR>"+
		"<A HREF= javascript:void(CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8',450,350,2)) >Mensaje SWIFT</A><BR>"+
		"<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

		// COMERCIAL MAINTENANCE
		var lc_apr_cc_maint =
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5101>Información Básica</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5103>Datos del Credito</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5105>Comisiones</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5106&fcode=ELC0510>Códigos Especiales</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5104&fcode=ELC0510>Instruccciones Especiales</A><BR>"+
  		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5102>Documentos Requeridos</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=39C>Montos Adicionales Cubiertos (39C)</A><BR>" +
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=41D>Credito Disponible Con (41D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42C>Giros A.. (42C)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42D>Banco Girado (42D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42M>Detalle de Pago Mixto (42M)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42P>Detalle de Pago Diferido (42P)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=44D>Período de Embarque (44D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=45A>Descripción de Mercancía (45A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=46A>Documentos Requeridos (46A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=47A>Condiciones Adicionales (47A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=48>Período para Presentación (48)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=71B>Cargos (71B)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=72>Información de Banco a Banco (72)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=77A>Narrativa para el Banco Reembolsador (77A)</A><BR>"+
  		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=78>Instrucciones para el Banco Pagador (78)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=79&CODE=79>Narrativa (79)</A><BR>"+
		"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=1>Aprobar</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=2>Eliminar</A><BR>"+
		"<A HREF=javascript:void(CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8',450,350,2)) >Mensaje SWIFT</A><BR>"+
		"<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

		// COMERCIAL TRANSFER
		var lc_apr_tranfer_cc_maint =
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5101>Información Básica</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5103>Datos del Credito</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5105>Comisiones</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5106&fcode=ELC0510>Códigos Especiales</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5104&fcode=ELC0510>Instruccciones Especiales</A><BR>"+
  		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5102>Documentos Requeridos</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=39C>Montos Adicionales Cubiertos (39C)</A><BR>" +
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=41D>Credito Disponible Con (41D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42C>Giros A.. (42C)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42D>Banco Girado (42D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42M>Detalle de Pago Mixto (42M)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=42P>Detalle de Pago Diferido (42P)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=44D>Período de Embarque (44D)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=45A>Descripción de Mercancía (45A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=46A>Documentos Requeridos (46A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=47A>Condiciones Adicionales (47A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=48>Período para Presentación (48)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=71B>Cargos (71B)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=72>Información de Banco a Banco (72)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=77A>Narrativa para el Banco Reembolsador (77A)</A><BR>"+
  		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=78>Instrucciones para el Banco Pagador (78)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=51&fcode=79&CODE=79>Narrativa (79)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=5107>Tranferencias</A><BR>"+
		"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=1>Aprobar</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=2>Eliminar</A><BR>"+
		"<A HREF=javascript:void(CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8',450,350,2)) >Mensaje SWIFT</A><BR>"+
		"<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

		// STANDBY
		var lc_apr_standby =
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=4001>Información Básica</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=4003>Datos del Credito</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=4005>Comisiones</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=4006&fcode=ELC0400>Códigos Especiales</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=4004&fcode=ELC0400>Instruccciones Especiales</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=40&fcode=45A>Descripción de Mercancía (45A)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=40&fcode=72>Información de Banco a Banco (72)</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=40&fcode=77C>Detalles De La Garantia  (77C)</A><BR>"+
		"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=1>Aprobar</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525?SCREEN=2>Eliminar</A><BR>"+
		"<A HREF=javascript:void(CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8',450,350,2)) >Mensaje SWIFT</A><BR>"+
		"<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


 var lc_transfer_detail =
  "<A HREF= javascript:goMsgSwift();>Mensaje SWIFT</A><BR>"+
  "<A HREF=javascript:goAction('A');>Aprobar    </A><BR>"+
  "<A HREF=javascript:goAction('D');>Eliminar   </A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Letter of Credit Maintenance

var lc_maint =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=5>Datos del Credito</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=7>Comisiones</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=201>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=103>Instrucciones Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=601>Documentos Requeridos</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=39C>Montos Adicionales Cubiertos (39C)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=41D>Credito Disponible Con (41D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=42C>Giros A.. (42C)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=42D>Banco Girado (42D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=42M>Detalle de Pago Mixto (42M)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=42P>Detalle de Pago Diferido (42P)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=44D>Período de Embarque (44D)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=45A>Descripción de Mercancía (45A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=46A>Documentos Requeridos (46A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=47A>Condiciones Adicionales (47A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=48>Período para Presentación (48)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=71B>Cargos (71B)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=72>Información de Banco a Banco (72)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=77A>Narrativa para el Banco Reembolsador (77A)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=78>Instrucciones para el Banco Pagador (78)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0510?SCREEN=50&CODE=79>Narrativa (79)</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Letter of Credit Nego Approval

	var lc_nego_apr =
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0100?SCREEN=8>Datos del Pago</A><BR>"+
		"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0100?SCREEN=7>Entradas Contables</A><BR>" +
		"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
		"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
		"<A HREF= \"javascript:goOpenerAction('D')\" >Eliminar</A><BR>" +
		"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
		"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// Documentary Debt Collections Maintenance

var dc_d_maint =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0000?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0000?SCREEN=5>Comisiones</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0000?SCREEN=7>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0000?SCREEN=9>Instrucciones Especiales</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Single Debt Collections Maintenance

var dc_s_maint =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0010?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0010?SCREEN=5>Comisiones</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0010?SCREEN=7>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDC0010?SCREEN=9>Instrucciones Especiales</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Documentary Debt Collections Approval Inquiry

var dc_d_inquiry =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=6>Comisiones</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=7>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=8>Instrucciones Especiales</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('D')\" >Eliminar</A><BR>"+
  "<A HREF=javascript:void(CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8',450,350,2)) >Mensaje SWIFT</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Single Debt Collections Approval Inquiry

var dc_s_inquiry =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=6>Comisiones</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=7>Códigos Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEDC0040?SCREEN=8>Instrucciones Especiales</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('D')\" >Eliminar</A><BR>"+
  "<A HREF=javascript:void(CenterWindow('<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=8',450,350,2)) >Mensaje SWIFT</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// Line of Credit approval

var cl_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000A?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000A?SCREEN=21>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000A?SCREEN=15>Instrucciones Especiales</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Line of Credit inquiry
var cl_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=1>Saldos</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=5>Códigos de Clasificación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=7>Instrucciones Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=9>Lineas de Control</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var ecif10_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=5>Totales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=11>Posición del Cliente</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=7>Consulta Gerencial</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=9>Lista de Cuentas</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1>Líneas de Crédito</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECP001?SCREEN=2>Lista de Productos</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF010?SCREEN=13>Consulta Tarjeta de Credito</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECP001?SCREEN=2>Rentabilidad</A><BR>"+

  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//ECIF170


var ecif170_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF170?SCREEN=5>Totales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF170?SCREEN=6>Posición por Cuenta</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF170?SCREEN=7>Posición por Producto</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Approvals

//CD approvals

var cd_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=33>Instrucciones de Renovación/Pago</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=21>Códigos Especiales</A> <BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=7>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=17>Beneficiarios</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=15>Instrucciones Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=19>Cambio de Tasa</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=47>Entradas Contables</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Loans Approvals

var ln_a_opt =
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=3>Información Básica </A><BR>"+
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=25>Detalle del Desembolso </A><BR>"+
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=17>Plan de Pagos</A><BR>" +
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEXEDL0160?SCREEN=12>Garantías Asignadas</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=15>Códigos Especiales</A><BR>" +
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=7>Titulares</A><BR> "  +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=11>Instrucciones Especiales</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=13>Cambio de Tasa</A><BR> " +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=33>Suspensión Débito Autom.</A><BR> " +
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0150A?SCREEN=28>Entradas Contables</A><BR> " +
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0152?SCREEN=4>Ajuste de FECI</A><BR> " +
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0153?SCREEN=7>Ajuste Interes DGI</A><BR> " +
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0305?SCREEN=3>Relaciones</A><BR>" +
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
    "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
        "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Loans Transactions Approval
var ln_t_a_opt =
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0152A?SCREEN=5>Transacciones</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0152A?SCREEN=27>Transacciones Adicionales</A><BR> " +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar/A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//CD Transactions Approval
var cd_t_a_opt =
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=5>Transacciones</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=47>Transacciones Adicionales</A><BR> " +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Credit Cards Approvals

var cc_a_opt =
 	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010A?SCREEN=3>Información Básica</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060A?SCREEN=5>Tarjeta Primaria</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060A?SCREEN=13>Tarjetas Adicionales</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010A?SCREEN=7>Códigos Especiales</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0010A?SCREEN=9>Instrucciones Especiales</A><BR>" +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Retail Accounts Approvals

var rt_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=5>Sobregiros</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=7>Cliente/Linea de Credito</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=11>Control de Lavado de Dinero</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=15>Cambio de Status de la Cuenta</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=13>Codigos Especiales</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0060A?SCREEN=15>Tarjetas de Débito</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=17>Titulares</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=23>Beneficiarios</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=21>Instrucciones Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=47>Representantes Legales</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=5>Mensajes del Cliente</A><BR> " +        
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Savings Accounts Approvals

var sv_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=27>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=5>Sobregiros</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=7>Cliente/Linea de Credito</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=11>Control de Lavado de Dinero</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=15>Cambio de Status de la Cuenta</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD5500I?SCREEN=19>Firmantes </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=13>Codigos Especiales </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=17>Titulares</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=23>Beneficiarios</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=21>Instrucciones Especiales</A><BR> "+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDD0000A?SCREEN=47>Representantes Legales</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=A>Documentación</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF=<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=5>Mensajes del Cliente</A><BR> " +        
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

  // Approval Corporative Customer

var client_ap_corp_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=11>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=31>Códigos de Clasificación</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=33>Direcciones de Correo</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=35>Instrucciones Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=37>Comunicaciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=39>Referencias Bancarias</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=41>Referencias Comerciales</A><BR>" +
//  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=43>Referencias Personales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=13>Accionistas</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=15>Junta Directiva</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=45>Representantes Legales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400&TYPE=CUST>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=17>Activos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=19>Pasivos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=21>Balance</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=C>Documentación </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01&APP_CODE=00>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01&APP_CODE=00')>Formularios (PDF)</A><BR>" +
//  "<A HREF= javascript:getIF01Forms('<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM030?SCREEN=1')>Formularios (IF01)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=47>Mercadeo</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=11>Mensajes del Cliente</A><BR> " +	
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

  // Approval Personal Customer option

var client_ap_personal_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=1>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=31>Códigos de Clasificación</A><BR>"  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=3>Beneficiarios</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=33>Direcciones de Correo</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=35>Instrucciones Especiales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=37>Comunicaciones</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=5>Datos de Empleo</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=39>Referencias Bancarias</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=41>Referencias Comerciales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=43>Referencias Personales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=45>Representates Legales</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0240?SCREEN=400&TYPE=CUST>Historial de Mantenimiento</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.tools.JSEDI0010?SCREEN=3&Type=C>Documentación </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01&APP_CODE=00>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01&APP_CODE=00')>Formularios (PDF)</A><BR>" +
//  "<A HREF= javascript:getIF01Forms('<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM030?SCREEN=1')>Formularios (IF01)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSESD0080A?SCREEN=47>Mercadeo</A><BR> "  +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0090?SCREEN=11>Mensajes del Cliente</A><BR> " +	
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Collaterals Maintenance

var colla_M_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011?SCREEN=1>Información Básica </A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011?SCREEN=9>Códigos de Clasificación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011?SCREEN=11>Instrucciones Especiales</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011?SCREEN=3>Otras Inscripciones</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011?SCREEN=15>Detalle de Polizas de Seguro</A><BR>"+
 "<A HREF= javascript:confirmDelete('<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0011?SCREEN=16')>Eliminar</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Collaterals Approval

var colla_A_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0080A?SCREEN=200>Información Básica </A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0080A?SCREEN=21>Códigos de Clasificación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0080A?SCREEN=15>Instrucciones Especiales</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0100?SCREEN=3>Otras Inscripciones</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0100?SCREEN=10>Detalle de Polizas de Seguro</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//collaterals Inquiry

var colla_i_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0100?SCREEN=1>Resumen </A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0080A?SCREEN=200>Información Básica </A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0080A?SCREEN=21>Códigos de Clasificación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0080A?SCREEN=15>Instrucciones Especiales</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0100?SCREEN=3>Otras Inscripciones</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSERA0100?SCREEN=10>Detalle de Polizas de Seguro</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Paying and Receiving Swift

var tranS_i_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=7>Documento</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Paying and Receiving Fed

var tranF_i_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=9>Documento</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Paying and Receiving Telex

var tranT_i_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=11>Documento</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Official Checks

//Print from Maintenance

var of_p_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=1>Información B&aacute;sica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115IA?SCREEN=500>Emision/Contabilidad</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=11>Entradas Contables</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=9>Cambio de Número</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=7>Imprimir </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//No Print from Maintenance

var of_np_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=1&RD=1>Informaci&oacute;n B&aacute;sica</A><BR>"+
// "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115IA?SCREEN=500>Emisión/Contabilidad</A><BR>"+
// "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=11>Entradas Contables</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=9>Cambio de N&uacute;mero</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Approval

var of_a_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=12>Informaci&oacute;n B&aacute;sica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115IA?SCREEN=500>Emisión/Contabilidad</A><BR>"+
 //"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=11&PURPOSE=APPROVAL>Entradas Contables</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= \"javascript:window.top.opener.document.forms[0].submit();window.close()\" >Aprobar</A><BR>" ;

//Print
//Official Check Printing Using forms
var of_p1_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115P?SCREEN=7>Imprimir </A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Official Check Printing using PDF 
var of_p2_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=12>Informacion Basica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115IA?SCREEN=500>Emisión/Contabilidad</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115?SCREEN=11&PURPOSE=APPROVAL>Entradas Contables</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115P?SCREEN=7>Imprimir </A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
// "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEOF0115P?SCREEN=100>Regresar</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Descuento Documentos
var dft_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=3>Información Básica </A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=15>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=7>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=11>Instrucciones Especiales<BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=27>Entradas Contables</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=41>Suspensión de Débito Automático</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var dft_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1600>Información Básica </A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=39>Saldos</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=37>Detalle del Desembolso</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=31>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=29>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=30>Instrucciones Especiales<BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=32&Purpose=READONLY>Entradas Contables</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0300L?SCREEN=200>Estado de Cuentas</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0816?SCREEN=1>Lista de Documentos</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var dft_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=1700&REQFLG=3>Información Básica </A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=38>Detalle del Desembolso </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=35>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=33>Titulares</A><BR> "  +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=34>Instrucciones Especiales<BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0800?SCREEN=32&Purpose=READONLY>Entradas Contables</A><BR> " +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\">Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\">Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Boleta Garantia maintenance 25/09/2002

var bg_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=8>Informaci&oacute;n B&aacute;sica</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=20>Aviso de Pago Beneficiario</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=1>Avales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=3>Prorroga</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
  "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bg_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5000?SCREEN=7>Informaci&oacute;n B&aacute;sica</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.trade.JSESD0817B?SCREEN=1>Estado de Cuentas</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.htm>Salir</A>";

//Aprobacion de Tesoreria
var cv_a_opt =
 	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Financial Transaction
var pr_a_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=100>Informaci&oacute;n B&aacute;sica</A><BR> " +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=200>Transacciones Adicionales</A><BR> " +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000?SCREEN=100&FromRecord=0&PURPOSE=APPROVAL>Transferencia M&uacute;ltiple</A><BR> " +
 "<A HREF= \"javascript:getSwift()\" >Formato Swift</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('J')\" >Devolver por Ordenante</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('K')\" >Devolver por Beneficiario</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var pr_r_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=100>Informaci&oacute;n B&aacute;sica</A><BR> " +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSEPR1080A?SCREEN=200>Transacciones Adicionales</A><BR> " +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= \"javascript:goOpenerAction('X')\" >Rechazar</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var pr_m_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000?SCREEN=3>Informaci&oacute;n B&aacute;sica</A><BR> " +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000?SCREEN=5>Transacciones Adicionales</A><BR> " +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000?SCREEN=100&FromRecord=0>Transferencia M&uacute;ltiple</A><BR> " +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=1&OPE_CODE=01>Formularios</A><BR>" +
 "<A HREF= javascript:getPdfForms('<%=request.getContextPath()%>/pages/s/EFRM000_pdf_form.jsp?SCREEN=1&OPE_CODE=01')>Formularios (PDF)</A><BR>" +
 "<A HREF= javascript:getIF01Forms('<%=request.getContextPath()%>/servlet/datapro.eibs.reports.JSEFRM030?SCREEN=1')>Formularios (IF01)</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var pr_inq_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR1060?SCREEN=3>Informaci&oacute;n B&aacute;sica</A><BR> " +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPR0000?SCREEN=100&FromRecord=0&PURPOSE=INQUIRY>Transferencia M&uacute;ltiple</A><BR> " +
 "<A HREF= \"javascript:getLogs()\" >Consulta de Logs</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Foreign Exchange

var fx_i_opt =

  "<A HREF= \"javascript:GetFXform();\">Formularios</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";



//Foreign Exchange

//CDS
var cd_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var cd_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Codigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//TDS
var td_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=33>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var td_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//FFS
var ff_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=37>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var ff_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//TPS
var tp_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=35>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var tp_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//ACC
var ac_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=49>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var ac_bo_act_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=63>Renovación</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=65>Lavado de Dinero</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=61>Códigos Especiales</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=51>Titulares</A><BR> "  +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=57>Beneficiarios</A><BR>" +
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130B?SCREEN=55>Instrucciones Especiales</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130B?SCREEN=49>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//Spot
var sf_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=73>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fw_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=75>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var nd_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=45>Mensaje de Confirmación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Option
var op_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=7>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=47>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=77>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Swap
var sw_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=49>Confirmation Spot</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=59>Confirmation Forward</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=79>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//PLP
var cp_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120B?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0120B?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=7>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100B?SCREEN=11>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//FRA
var fra_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSETR0130?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=1>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=3>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//Maintenance

//Swap
var swm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=9>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=49>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=87>Notice to Receive</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Option
var opm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=85>Notice to Receive</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var ndm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=5>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fwm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=43>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=85>Notice to Receive</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>" ;

//Spot
var sfm_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100M?SCREEN=81>Notice to Receive</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Approval
//Swap
var swa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=9>Información Básica</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=39>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=49>Confirmation Spot<BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=59>Confirmation Forward</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=67>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
   "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Option
var opa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=7>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=37>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=47>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=65>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var nda_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=5>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=35>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=45>Mensaje de Confirmación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fwa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=3>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=33>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=43>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=63>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Spot
var sfa_bo_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=1>Información Básica</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=31>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=41>Mensaje de Confirmación</A><BR>"+
    "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100A?SCREEN=61>Notice to Receive</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Deals

var cdt_a_opt =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=3>Información Básica</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=33>Renovación</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=21>Códigos Especiales</A><BR>" +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130A?SCREEN=61>Mensaje Swift</A><BR>"+
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130A?SCREEN=63>Mensaje de Confirmación</A><BR>"+
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var cdt_a_act_opt =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=3>Información Básica</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=33>Renovación</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0130A?SCREEN=21>Códigos Especiales</A><BR>" +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130A?SCREEN=63>Mensaje de Confirmación</A><BR>"+
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//FRA

//FRA
var fra_app_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0140A?SCREEN=80>Información Básica</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=5>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0340?SCREEN=7>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// Deals back office maintenance

var cdm_m_opt =
	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=3>Información Básica </A><BR>"+
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=33>Renovación</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=52>Lavado de Dinero</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=21>Códigos Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=7>Titulares</A><BR> "  +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=17>Beneficiarios</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=15>Instrucciones Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=19>Rate Change</A><BR> " +
	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130M?SCREEN=61>Mensaje Swift</A><BR>"+
  	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130M?SCREEN=63>Mensaje de Confirmación</A><BR>"+
  	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
     "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var cdm_m_act_opt =
	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=3>Información Básica </A><BR>"+
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=33>Renovación</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=52>Lavado de Dinero</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=21>Códigos Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=7>Titulares</A><BR> "  +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=17>Beneficiarios</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=15>Instrucciones Especiales</A><BR>" +
     "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130M?SCREEN=19>Rate Change</A><BR> " +
	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  	 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130M?SCREEN=63>Mensaje de Confirmación</A><BR>"+
  	 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
     "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Foreign Exchange Inquiry

//Option
var opi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=7>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=37>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=47>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=65>Notice to Receive</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//NDF
var ndi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=5>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
    "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=35>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=45>Mensaje de Confirmación</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Forward
var fwi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=3>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=39>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=59>Mensaje de Confirmación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=63>Notice to Receive</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Spot
var sfi_bo_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=1>Información Básica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEFE0160?SCREEN=55>Instrucciones Especiales</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=31>Mensaje Swift</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=41>Mensaje de Confirmación</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF100I?SCREEN=61>Notice to Receive</A><BR>"+
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Payment Approval
var swf_a_opt =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEWD0341A?SCREEN=3>Mensaje Swift</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.transfer.JSEWD0341A?SCREEN=5>Logs Inquiry</A><BR>" +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Approval</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Reject</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Treasury Deals Inquiry
var cdt_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=57>Account Statement</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=13>Balances </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=41>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=56>Recap</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=27>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEDL0130IT?SCREEN=31>Instrucciones Especiales</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130I?SCREEN=61>Mensaje Swift</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSESWF0130I?SCREEN=63>Mensaje de Confirmación</A><BR>"+
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//Investments Instruments
var inst_basic_opt =
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0050?SCREEN=400>Informacion Basica </A><BR>"+
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0060?SCREEN=4>Cuotas</A><BR>"+
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0070?SCREEN=900>Plan de Pagos</A><BR>" +
  "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var inst_inq_opt =
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0050?SCREEN=800>Informacion Basica</A><BR>"+
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0060I?SCREEN=4>Cuotas</A><BR>"+
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0070I?SCREEN=900>Plan de Pagos</A><BR>" +
  "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var inst_app_opt =
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0050?SCREEN=1000>Informacion Basica</A><BR>"+
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0060A?SCREEN=4>Cuotas</A><BR>"+
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0070A?SCREEN=900>Plan de Pagos</A><BR>" +
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+

  "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var trade_a_ticket_opt =
  "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0130A?SCREEN=400>Informacion Basica </A><BR>"+
	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
   	"<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

 var inv_port_opt =
 "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0010?SCREEN=1>Portafolio </A><BR>"+
 "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0010?SCREEN=3>Cuentas Transaccionales</A><BR>"+

 "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var inv_i_port_opt =
 "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0010I?SCREEN=1>Portafolio </A><BR>"+
 "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0010I?SCREEN=3>Cuentas Transaccionales</A><BR>"+

 "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var inv_a_port_opt =
 "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0010A?SCREEN=1>Portafolio </A><BR>"+
 "<A HREF= /eIBS_R04M03/servlet/datapro.eibs.invest.JSEIE0010A?SCREEN=3>Cuentas Transaccionales</A><BR>"+
 	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
	"<A HREF= \"javascript:appPrint();\" >Print</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  	"<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  	"<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var coupon_ap_opt =
 "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var free_a_opt =
 "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
 "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
 "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
 "<A HREF= /eIBS_R04M03/pages/background.jsp>Salir</A>";

var cr_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF200?SCREEN=400>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF200?SCREEN=300>Productos Vigentes</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF250?SCREEN=1>Cuentas Corrientes</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF260?SCREEN=1>Comportamiento</A><BR>"+
  "<A HREF=<%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var prd_loan_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.client.JSECIF200?SCREEN=400>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/com.datapro.eibs.parameters.loans.servlet.JSLoansParameters?SCREEN=1>Tablas Tarifa</A><BR>"+
  "<A HREF='javascript:checkClose()'>Salir</A>";


// CP maintenance

var cp_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=5>Cambio de Tasa</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=9>Precio de Mercado </A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105?SCREEN=7>Instrucciones de Pago</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// CP Inquiry

var cp_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=13>Estado de Cuenta</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=11>Resumen de Seguridad</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=9>Precio de Mercado</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105I?SCREEN=7>Instrucciones de Pago</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//Approval

// CP approvals

var cp_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=3>Información Básica</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=9>Precio de Mercado</A><BR> " +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0105A?SCREEN=7>Instrucciones de Pago</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">" +
  "<A HREF= \"javascript:appPrint();\" >Imprimir</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


//Sistema de Bastanteo
var bastanteo_menu_1 =
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=30>Cuentas Juridicas Relacionadas</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=40>Reformas Documento Constitutivo</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=50>Integrantes de la Administracion</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bastanteo_menu_1_1 =
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=30>Cuentas Juridicas Relacionadas</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=40>Reformas Documento Constitutivo</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=50>Propietario/Apoderado</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bastanteo_menu_2 =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=60>Autorizaciones Especiales</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=70>Facultades Limitadas</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bastanteo_menu_3 =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=80>Firmas Conjuntas Especificas</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bastanteo_menu_inq_1 =
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=35>Cuentas Juridicas Relacionadas</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=45>Reformas Documento Constitutivo</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=55>Integrantes de la Administracion</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bastanteo_menu_inq_1_1 =
  	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=35>Cuentas Juridicas Relacionadas</A><BR>"+
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=45>Reformas Documento Constitutivo</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=55>Propietario/Apoderado</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bastanteo_menu_inq_2 =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=65>Autorizaciones Especiales</A><BR>" +
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=75>Facultades Limitadas</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

var bastanteo_menu_inq_3 =
	"<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=85>Firmas Conjuntas Especificas</A><BR>" +
   	"<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";
   	
   	// Line of Credit maintenance

var cl_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000?SCREEN=21>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000?SCREEN=15>Instrucciones Especiales<BR>" +
  "<A HREF= javascript:confirmDelete('<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000?SCREEN=5')>Eliminar</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Line of Credit inquiry

var cl_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=1>Saldos</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=5>Códigos de Clasificación</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=7>Instrucciones Especiales</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0060?SCREEN=9>Lineas de Control</A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Line of Credit approval

var cl_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000A?SCREEN=3>Información Básica </A><BR>"+
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000A?SCREEN=21>Códigos Especiales</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0000A?SCREEN=15>Instrucciones Especiales</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";
   	

function callBastanteo() {

    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.bastanteo.JSECU0000?SCREEN=700";
    	CenterNamedWindow(page,'Information',650,500,2);
}

// Project Control Maintenance

var pc_m_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=400>Información Básica</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0005?SCREEN=200>Viviendas</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0010?SCREEN=200>Participantes Pool</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0015?SCREEN=200>Inspectores Obra</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0085?SCREEN=400>Cálculo de Factores</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0300?SCREEN=200>Presupuesto de Obra</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=21>Códigos Especiales</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=7>Titulares</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

// Project Control Inquiry

var pc_i_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=600>Información Básica</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0005?SCREEN=201>Viviendas</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0010?SCREEN=201>Participantes Pool</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0015?SCREEN=201>Inspectores Obra</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0085?SCREEN=600>Cálculo de Factores</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0300?SCREEN=201>Presupuesto de Obra</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0115?SCREEN=100>Cambios de Status</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0135?SCREEN=100>Créditos Asociados</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0145?SCREEN=100>Deuda Global</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0170?SCREEN=100>Historia de Valuaciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0180?SCREEN=201>Condominio/Factor Und.Vend</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0130?SCREEN=2>Transacciones</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=23>Códigos Especiales</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=9>Titulares</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";


// Project Control Approval

var pc_a_opt =
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=600>Información Básica</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0005?SCREEN=201>Viviendas</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0010?SCREEN=201>Participantes Pool</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0015?SCREEN=201>Inspectores Obra</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0085?SCREEN=600>Cálculo de Factores</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0300?SCREEN=201>Presupuesto de Obra</A><BR>" +
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=23>Códigos Especiales</A><BR>" + 
  "<A HREF= <%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEPC0000?SCREEN=9>Titulares</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= \"javascript:goOpenerAction('A')\" >Aprobar</A><BR>" +
  "<A HREF= \"javascript:goOpenerAction('R')\" >Rechazar</A><BR>" +
  "<hr align=\"center\" style=\"height:1pt;color:navy\">"+
  "<A HREF= <%=request.getContextPath()%>/pages/background.jsp>Salir</A>";

//end help menu def

// Functions adde from optMenu_Extended

