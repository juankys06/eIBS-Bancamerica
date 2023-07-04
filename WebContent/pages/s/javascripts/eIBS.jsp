<%@ page contentType="application/x-javascript" %>
<%@ page import = "datapro.eibs.sockets.DecimalField" %>

// Global variable for english
var errorTitle="Errores"
var fieldName;
var fieldDesc;
var fieldId;
var fieldAux1;
var fieldAux2;
var option;
var language = "s/";
var prefix = "<%=request.getContextPath()%>/pages/";
var imgPath = "<%=request.getContextPath()%>/images/";
var webapp = "<%=request.getContextPath()%>";
var msgRightClick = "Todos los derechos reservados";
var errorwin = null;
var amountformat = true;
var decSeparator = '<%= DecimalField.getDecimalSeparator() %>';
var grpSeparator = '<%= DecimalField.getGroupingSeparator()%>';
var decimalPartDefaultLength = 2;

// Speaker
var applet=null;
var model;

function GetCodeTChannel(name,desc,aux1)
{
	fieldName=name;
	fieldDesc=desc;
	fieldAux1=aux1;
	page= prefix +language + "EPR0300_chanel_codes_helpfile.jsp";
	CenterWindow(page,450,300,2);
}

function GetFeRef(name,typ,prd,desc){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0325";
	fieldName=name;
	fieldAux1=typ;
	fieldAux2=prd;
	fieldAux3=desc;
	CenterWindow(page,500,230,3);
}

function GetFeRefNew(name,typ,prd){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0325";
	fieldName=name;
	fieldAux1=typ;
	fieldAux2=prd;
	CenterWindow(page,500,230,3);
}

function GetUser(code,name,usr){

	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0430?USR=" + usr;
	fieldName=code;
	fieldAux1=name;
	CenterWindow(page,500,450,3);

}

function GetGroupId(code){

	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0435";
	fieldName=code;
	CenterWindow(page,500,450,3);

}


function GetCloneTransfer(name)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEPR1060H?SCREEN=1";
	fieldName=name;
	CenterWindow(page,600,350,3);
}

function GetCodeTransaction(name,desc,aux1)
{
	fieldName=name;
	fieldDesc=desc;
	fieldAux1=aux1;
	page= prefix +language + "EPR0300_transaction_codes_helpfile.jsp";
	CenterWindow(page,450,300,2);
}

function GetComission(ref) {

page = webapp + "/servlet/datapro.eibs.helps.JSEWD0530?Ref=" + ref;

CenterWindow(page,400,280,1);

}


function checkClose() {
	if ( window.name !="main" ) top.close();
	else window.location.href = "<%=request.getContextPath()%>/pages/background.jsp";
}

function GetMxBank(name)
{
	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0810";
	CenterWindow(page,600,270,1);
}

function GetPlastic(num)
{
	fieldNum=num;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0410";
	CenterWindow(page,300,150,2);
}

function GetCodeService(srv,srn,cia)
{
	fieldSrv=srv;
	fieldSrn=srn;
    	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0520?Cia=" + cia;
	CenterWindow(page,500,350,2);

}

function GetSequence(seq,cia,srv)
{
	fieldSeq=seq;
    	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0525?Cia=" + cia + "&Srv=" + srv;
	CenterWindow(page,500,350,2);

}



function GetPlastic2(num, codeflag)
{
	GetPlastic2(num, '', codeflag)
}

function GetPlastic2(num, type, codeflag)
{
	fieldName=num;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0411?type=" + type + "&codeflag=" + codeflag;
	CenterWindow(page,600,300,2);
}

function GetPlastic3(num, type, branch, user, codeflag)
{
	fieldName=num;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0413?type=" + type + "&branch=" + branch + "&user=" + user + "&codeflag=" + codeflag;
	CenterWindow(page,600,300,2);
}

//*************************************************************************
//ESTA AYUDA TRAE EL NRO DE LA TARJETA DEBITO Y ACTUALIZA EN LA PANTALLA QUE LO ESTA LLAMANDO
//LOS CAMPOS NRO DE TARJETA FECHA DE EXPEDICION Y FECHA DE EXPIRACION

var monthEx ; 		// Mes Expiracion
var yearEx ;		// Anio Expiracion
var dayEm ;			// Day Emision
var monthEm ;		// Mes Emision
var yearEm ;		// Anio Emision

function GetPlastic4(num, mEx, yEx, dEm, mEm, yEm, type, branch, user, codeflag)
{
	fieldName=num;
	monthEx = mEx ;
	yearEx = yEx ;
	dayEm = dEm ;
	monthEm = mEm ;
	yearEm = yEm ;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0413A?type=" + type + "&branch=" + branch + "&user=" + user + "&codeflag=" + codeflag;
	CenterWindow(page,600,300,2);
}
//***************************************************************************


function GetPlasticStatus(num, desc)
{
	fieldName=num;
	fieldDesc=desc;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0414";
	CenterWindow(page,600,300,2);
}

function GetTransactionType(num, desc)
{
	fieldName=num;
	fieldDesc=desc;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0415";
	CenterWindow(page,600,300,2);
}

function GetCommissionType(num, desc)
{
	fieldName=num;
	fieldDesc=desc;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0416";
	CenterWindow(page,600,300,2);
}

function GetPaymentPeriod(num, desc)
{
	fieldName=num;
	fieldDesc=desc;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0417";
	CenterWindow(page,600,300,2);
}

function GetMxSquare(name)
{
	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0815";
	CenterWindow(page,600,270,1);
}

function GetDescATMCard(name,desc)
{
	page= webapp + "/servlet/datapro.eibs.helps.JSEWD0135";
	fieldName=name;
	fieldDesc=desc;
	CenterWindow(page,400,300,1);
}

function GetDocInv(name)
{
	page= webapp + "/servlet/datapro.eibs.helps.JSEWD0165";
	fieldName=name;
	CenterWindow(page,400,300,2);
}

function NewUpdateShortCust(name,desc,cusnum,cusidn) {
	fieldName=name;
	fieldDesc=desc;
	page = webapp + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=7&CUSNUM=" + cusnum + "&CUSIDN=" + cusidn;
	CenterWindow(page,600,500,2);
}

function DOBPicker(d1,d2,d3)
{
    fieldDate1 = d1;
    fieldDate2 = d2;
    fieldDate3 = d3;
    fieldAux1 = false;
    page= prefix +language + "STATIC_date_picker.jsp";
    CenterWindow(page,270,270,5);
}

function DatePicker(d1,d2,d3)
{
    fieldDate1 = d1;
    fieldDate2 = d2;
    fieldDate3 = d3;
    fieldAux1 = true;
    page= prefix +language + "STATIC_date_picker.jsp";
    CenterWindow(page,270,270,5);
}

function eIBSPrint() {
   window.focus();
   window.print();
   return;
}

function replaceAll(value,char){

  var result = value;
  var posi = value.indexOf(char);
  if(posi > -1){
	  while(posi > -1){
		  result = value.substring(0,posi);
	      result = result + value.substring(posi+1);
	      posi = result.indexOf(char);
	      value = result;
	  }
  }

  return(result);

 }

function formatCCY(num) {
 	var num2 = "00";
 	var result = "";
 	var sign = "";
 	sign = num.substring(0,1);
 	if(sign == "-") num = num.substring(1);
 	else sign="";

 	var posi = num.indexOf(".");
 	if(posi > -1){
	 num2 = num.substring(posi + 1, num.length);
	 num = num.substring(0, posi);
	 if (num2.length == 1) num2= num2 +"0";
	 else if (num2.length > 2) num2 = num2.substring(0, 2)
 	}
 	num2 = decSeparator + num2;
 	var count = 0;
 	var y = num.length -1;
 	for(x=y;x>-1;x--) {
		var nx = num.charAt(x);
		result = nx + result;
		++count;
		if(count == 3 || count == 6 || count == 9 || count == 12){
	  		if(x > 0) { result = grpSeparator + result; } // add grouping separator
		}

  	}

  	result = sign + result + num2;
  	return(result);
  }

 function roundFCCY(num) {
 	var num2 = "";
 	var result = 0;
 	//var num1 = replaceAll("" + num,grpSeparator);
 	var num1 = ""+num;
 	var sign = num1.substring(0,1);
 	if(sign == "-") num1 = num1.substring(1);
 	else sign="";
 	var posi = num1.indexOf(".");
 	if (posi != -1) {
 	 num2 = num1.substring(posi +1, num1.length);
 	 if (num2.length > 2) {
 	   if (parseInt(num2.substring(2, 3)) > 5) {
 	     result = parseFloat(num1.substring(0,posi + 3)) + 0.01;
 	     return(sign + result);
 	   } else return( sign + num1.substring(0,posi + 3));
 	 } else return("" + num);
 	} else return("" + num);

 }

function formatFloat(value) {
	if (value == "") return "0.00";
	value = replaceAll(value, grpSeparator)
	if (decSeparator != '.') value = value.replace(decSeparator, '.');
	return value
}

function formatValor(campo,preformat,dec) {

	var	vr = campo.value;
		vr = vr.replace( decSeparator, "" );
		vr = replaceAll( vr, grpSeparator );
	var sign = "";

    if (vr.indexOf('-') != -1) {
	    vr = replaceAll( vr, "-" );
    	sign = "-";
    }	
	var	tam = (preformat) ? vr.length : vr.length + 1;
	
	if ( tam <= dec ){
	    var strDecimals="";
	    for(i=0; i < dec-tam; i++){
	    	strDecimals = "0" + strDecimals;
	    }
 		campo.value = "0" + decSeparator + strDecimals + vr;
	} else {
	    var tamInt = tam - dec;
	    var result = "";
	    var count = 0;
	    for ( x = tamInt - 1; x > -1; x--) {
			var nx = vr.charAt(x);
			result = nx + result;
			++count;
			if (count == 3 || count == 6 || count == 9 || count == 12) {
				if (x > 0) {
					result = grpSeparator + result;
				} // add separator
			}
		}
		campo.value = result + decSeparator + vr.substr( tamInt, tam );
	}
		
	var pos = campo.value.indexOf(decSeparator);
		if (pos != -1) {
		vr = campo.value.substr( 0, pos );
		if (vr == "00" || (vr.length == 2 && vr.substr( 0, 1) == "0")) campo.value = campo.value.substr(1, tam);
	}
	campo.value = sign + campo.value;	
}

function isCharCodeNumber(kcode) {
	var isNumber = false;
	if (kcode > 47 && kcode < 58) isNumber = true; //Numbers - 48 --> 57
	return isNumber;
}

function isCharNumber(character) {
	if (character.length != 1) return false;
	return isCharCodeNumber(character.charCodeAt(0));
}

function addLeftChar(character,len,value) {
	if (value != '') {
		for(n=value.length;n<len;n++){
 		    value = character +  value;
		}
	}
	return value;
}

function rightAlignCharNumber() {
 	var elem=event.srcElement;
	var value = elem.value;
	var kcode;

	if (value != '') {
		for(n=0;n<value.length;n++){
			kcode = value.charCodeAt(n);
			if (!isCharCodeNumber(kcode)) return;
		}
		for(n=value.length;n<elem.maxLength;n++){
 		    elem.value = "0" +  elem.value;
		}
	}
}

function enterCharNumber() {
 var elem=event.srcElement;
 var kcode=event.keyCode;
 var newVal="";

 var kdata = String.fromCharCode(kcode);
 newVal = kdata;

 if (isCharCodeNumber(kcode))
 		if (elem.maxLength > elem.value.length) elem.value = elem.value + newVal;
 event.returnValue = false;
}

function checkDecimal(dec){
 	if (!dec) dec = decimalPartDefaultLength;

	var elem=event.srcElement;
	var kcode=event.keyCode;
  	switch (kcode){
    	case  8:
    	case 46:
	     { 	
	     	if (amountformat) {
	        	formatValor(elem,true,dec);
         	}
         	break; 
	     }
	}
}

function enterDecimal(dec) {
 if (!dec) dec = decimalPartDefaultLength;

 var elem=event.srcElement;
 if (elem.readOnly) return;
 var kcode=event.keyCode;
 var val;
 var newVal="";

 var kdata = String.fromCharCode(kcode);
 if (amountformat) {
   elem.value = formatFloat(elem.value);
   //elem.blur();
   //elem.focus();
  }

 switch (kcode){
   case 66:
   case 98:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
           if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000000000.00";
             else if (elem.maxLength - elem.value.length > 12) elem.value = elem.value + "000000000.00";
           }else elem.value = "1000000000.00";
         } else {
             val = parseFloat(elem.value) * 100000000000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "1000000000.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
         }
         if (amountformat) formatValor(elem,true,dec);
         break;
        }
   case 72:
   case 104:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
           if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "100.00";
             else if (elem.maxLength - elem.value.length > 5) elem.value = elem.value + "00.00";
           }else elem.value = "100.00";
         } else {
             val = parseFloat(elem.value) * 10000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "100.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
         }
         if (amountformat) formatValor(elem,true,dec);
         break;
        }
   case 77:
   case 109:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
			if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000000.00";
             else if (elem.maxLength - elem.value.length > 9) elem.value = elem.value + "000000.00";
           }else elem.value = "1000000.00";
    	 } else {
    	     val = parseFloat(elem.value) * 100000000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "1000000.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
    	 }
    	 if (amountformat) formatValor(elem,true,dec);
         break;
        }
   case 84:
   case 116:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
           if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000.00";
             else if (elem.maxLength - elem.value.length > 6) elem.value = elem.value + "000.00";
           }else elem.value = "1000.00";
         } else {
             val = parseFloat(elem.value) * 100000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "1000.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
         }
         if (amountformat) formatValor(elem,true,dec);
         break;
        }
   default: {
         if (amountformat) {
            if ((kcode < 48 || kcode > 57) && kcode != 13){
           		event.returnValue = false;
	  			formatValor(elem,true,dec);
	  		} else if (kcode != 13) {
   	       		formatValor(elem,false,dec);
   	       		elem.value = elem.value +  kdata;
   	       		event.returnValue = false;
   	       	}
         } else {
         		if ((kcode < 48 || kcode > 57) && kcode != 46 && kcode != 13){
         			event.returnValue = false;
         		} else if (kcode == 46 && elem.value.indexOf('.') != -1) {
         			event.returnValue = false;
         		}
		 }
        }
 }
  
}

function enterSignCCY(dec) {
 if (!dec) dec = decimalPartDefaultLength;

  var elem=event.srcElement;
  var kcode=event.keyCode;
  var val;
  var newVal="";
  switch (kcode){
   case 45:
        {
         event.returnValue = false;
         if(elem.value.indexOf('-')==-1) elem.value = "-" + elem.value; 
         break;
        }
   case 43:
        {
         event.returnValue = false;
         if(elem.value.indexOf('-')!==-1) elem.value = elem.value.substring(1,elem.value.length);           
         break;
        }
   default: {
        enterSignDecimal(dec);
        }
   }
 } 
 
function enterSignDecimal(dec) {
 if (!dec) dec = decimalPartDefaultLength;

 var elem=event.srcElement;
 var kcode=event.keyCode;
 var val;
 var newVal="";

  var kdata = String.fromCharCode(kcode);
  if (amountformat) {
   elem.value = formatFloat(elem.value);
  }

 switch (kcode){
   case 66:
   case 98:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
           if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000000000.00";
             else if (elem.maxLength - elem.value.length > 12) elem.value = elem.value + "000000000.00";
           }else elem.value = "1000000000.00";
         } else {
             val = parseFloat(elem.value) * 100000000000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "1000000000.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
             if (amountformat) formatValor(elem,true,dec);
         }
         break;
        }
   case 72:
   case 104:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
           if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "100.00";
             else if (elem.maxLength - elem.value.length > 5) elem.value = elem.value + "00.00";
           }else elem.value = "100.00";
         } else {
             val = parseFloat(elem.value) * 10000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "100.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
             if (amountformat) formatValor(elem,true,dec);
         }
         break;
        }
   case 77:
   case 109:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
			if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000000.00";
             else if (elem.maxLength - elem.value.length > 9) elem.value = elem.value + "000000.00";
           }else elem.value = "1000000.00";
    	 } else {
    	     val = parseFloat(elem.value) * 100000000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "1000000.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
             if (amountformat) formatValor(elem,true,dec);
    	 }
         break;
        }
   case 84:
   case 116:
        {
         event.returnValue = false;
         if(elem.value.indexOf('.')==-1) {
           if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000.00";
             else if (elem.maxLength - elem.value.length > 6) elem.value = elem.value + "000.00";
           }else elem.value = "1000.00";
         } else {
             val = parseFloat(elem.value) * 100000.00;
             newVal = "" + val;
             if(newVal.indexOf('.')==-1) newVal=newVal+".00";
             else {
               val = newVal.length - newVal.indexOf('.');
               if(val == 2) newVal = newVal+"0";
             }
             if (newVal=="0.00") elem.value = "1000.00";
             else if (elem.maxLength > newVal.length) elem.value = newVal;
             if (amountformat) formatValor(elem,true,dec);
         }
         break;
        }
   case 45:
        {
         event.returnValue = false;
         if(elem.value.indexOf('-')==-1) elem.value = "-" + elem.value;
		 if (amountformat) formatValor(elem,true,dec);
         break;
        }
   case 43:
        {
         event.returnValue = false;
         if(elem.value.indexOf('-')!==-1) elem.value = elem.value.substring(1,elem.value.length);
         if (amountformat) formatValor(elem,true,dec);
         break;
        }
   default: {
         if (amountformat) {
            if ((kcode < 48 || kcode > 57) && kcode != 13){
           		event.returnValue = false;
	  			formatValor(elem,true,dec);
	  		} else if (kcode != 13) {
   	       		formatValor(elem,false,dec);
   	       		elem.value = elem.value +  kdata;
   	       		event.returnValue = false;
   	       	}
         } else {
         		if ((kcode < 48 || kcode > 57) && kcode != 46 && kcode != 13){
         			event.returnValue = false;
         		} else if (kcode == 46 && elem.value.indexOf('.')!==-1) {
         			event.returnValue = false;
         		}
		 } 		
		}
 } 
}


function enterInteger() {
var elem=event.srcElement;
 var kcode=event.keyCode;
 switch (kcode){
   case 66:
   case 98:
        {
         event.returnValue = false;
         if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000000000";
             else if (elem.maxLength - elem.value.length > 9) elem.value = elem.value + "000000000";
           }else elem.value = "1000000000";
         break;
        }
   case 72:
   case 104:
        {
         event.returnValue = false;
         if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "100";
             else if (elem.maxLength - elem.value.length > 2) elem.value = elem.value + "00";
         }else elem.value = "100";
         break;
        }
   case 77:
   case 109:
        {
         event.returnValue = false;
         if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000000";
             else if (elem.maxLength - elem.value.length > 6) elem.value = elem.value + "000000";
         }else elem.value = "1000000";
         break;
        }
   case 84:
   case 116:
        {
         event.returnValue = false;
         if (elem.value.length > 0) {
             if (parseInt(elem.value)==0)  elem.value = "1000";
             else if (elem.maxLength - elem.value.length > 3) elem.value = elem.value + "000";
           }else elem.value = "1000";
         break;
        }
   default: {
        if ((kcode < 48 || kcode > 57) && kcode != 13) event.returnValue = false;
 		}
 }
}

function enterDecimalNum(evt)
{
	var charCode = (evt.which) ? evt.which : event.keyCode;
	// alert (charCode);
	if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode!=46 && charCode!=45)
        return false;
	return true;
}

function trim(value)
{

 var i = 0;
 var front = false;
 var rear = false;
 var j = value.length;

 while ((j > 0) && ((front == false) || (rear == false))){

  if (value.charAt(i) != " "){front = true;}
  else {
   value = value.substring(i+1);
   j = value.length -1;}

  if (value.charAt(j) != " "){
      rear = true;}
  else if (i == j-1) {
               value = value.charAt(i);
                   rear = true;
                     }
       else{
        value = value.substring(0,j-1);
        j = value.length -1;
           }
 }
 return value;
}

function radioClick(name,idx) {
//var oObject = document.all.item(name);
var oObject = document.getElementsByName(name);
if (oObject != null){
   if (oObject.length != null){
      oObject(idx).click();
   }
   else{
      oObject.click();
   }
 }
}

function go(op) {
   var dist= op -1
       dataDiv.scrollTop= dataDiv.clientHeight * dist;
}

function adjustEquTables(Table1,Table2,Div1,rb,footTb) {
   var T1= Table1.rows[0];
   var T2= Table2.rows[0];
   var wT1=0;
   var wT2=0;
   var maxCol=0;
   var incr=0;
   var maxWidth=0;
   var adjPerCol=0;
   var adjust=0;
   var mainTb= Table1.parentNode;

  if ( Table2.rows.length >= 1 ) {

   maxCol= T2.cells.length;
   incr= maxCol * 2;


   for(var i=0;i<maxCol;i++){
      T1.cells[i].style.pixelWidth=0;
      T2.cells[i].style.pixelWidth=0;

      wT1= T1.cells[i].clientWidth;
      wT2= T2.cells[i].clientWidth;

      T1.cells[i].style.pixelWidth= ( wT1 >= wT2 ) ? wT1: wT2;
      T2.cells[i].style.pixelWidth= ( wT1 >= wT2 ) ? wT1: wT2;
      maxWidth= ( wT1 >= wT2 ) ? maxWidth + wT1: maxWidth + wT2;

   }


   if (footTb) {
     footTable.rows[0].cells[0].style.pixelWidth= (maxWidth + 8 ) - T1.cells[maxCol-1].style.pixelWidth;
     footTable.rows[0].cells[1].style.pixelWidth= T1.cells[maxCol-1].style.pixelWidth;
   }
   maxWidth += incr;


   Table1.width= maxWidth;
   Table2.width= Table1.width;
   if (footTb) { footTable.width= Table1.width;}

   //incr= (footTb) ? 21 : 40;

   if ( Table1.clientWidth < mainTb.clientWidth ){
       incr= (Div1.style.overflowY=="scroll") ? Table1.clientWidth + 23: Table1.clientWidth;
       adjust= mainTb.clientWidth - incr ;
       adjPerCol= adjust / (maxCol - rb);
       adjPerCol= Math.round(adjPerCol);

     for(k=0;k<maxCol;k++){
       wT1= T1.cells[k].style.pixelWidth;
       T1.cells[k].style.pixelWidth= ( k == (rb-1) ) ? wT1: wT1 + adjPerCol;
       wT2= T2.cells[k].style.pixelWidth;
       T2.cells[k].style.pixelWidth= ( k == (rb-1) ) ? wT2: wT2 + adjPerCol;
     }
   if (footTb) {
     footTable.rows[0].cells[0].style.pixelWidth += adjPerCol * 5 ;
     footTable.rows[0].cells[1].style.pixelWidth += adjPerCol;
    }
   }

  }
  else{Table1.width="100%"}

}

function adjustDifTables(Table1,Table2,Div1,column,rb) {
   var T1= Table1.rows[0];
   var T11= Table1.rows[1];
   var maxT11Col = T11.cells.length -1;
   var wT1= 0;
   var wT2= 0;
   var n= column;
   var maxWidth=0;
   var adjPerCol=0;
   var adjust=0;
   var mainTb= Table1.parentNode; //TD

   if ( Table2.rows.length >= 1 ) {

     var T2= Table2.rows[0];
     var maxCol=Table2.rows[0].cells.length;
     var incr= maxCol * 2;

     for(i=0;i<n;i++){
      T1.cells[i].style.pixelWidth=0;
      T2.cells[i].style.pixelWidth=0;
      wT1= T1.cells[i].offsetWidth;
      wT2= T2.cells[i].offsetWidth;
      if ( wT1 > wT2 ) {
          T1.cells[i].style.pixelWidth = wT1;
          T2.cells[i].style.pixelWidth = wT1;
       }else{
          T1.cells[i].style.pixelWidth = wT2;
          T2.cells[i].style.pixelWidth = wT2;
       }
      maxWidth= ( wT1 > wT2 ) ? maxWidth + wT1: maxWidth + wT2;
     }

     for(i=n;i<maxCol;i++){

      if ( i>=n && i<=(n+maxT11Col) ) {
       T11.cells[i-n].style.pixelWidth=0;
       wT1= T11.cells[i-n].offsetWidth;
      } else {
        T1.cells[i-maxT11Col].style.pixelWidth=0;
        wT1= T1.cells[i-maxT11Col].offsetWidth; }

      T2.cells[i].style.pixelWidth=0;
      wT2= T2.cells[i].offsetWidth;

      if ( wT1 > wT2 ) {
         T2.cells[i].style.pixelWidth=wT1;
         if (  i>=n && i<=(n+maxT11Col) ) {
           T11.cells[i -n].style.pixelWidth=wT1;
         } else {
           T1.cells[i -maxT11Col].style.pixelWidth=wT1;
         }
      } else {
         T2.cells[i].style.pixelWidth=wT2;
         if ( i>=n && i<=(n+maxT11Col) ) {
          T11.cells[i -n].style.pixelWidth=wT2;
         } else {
          T1.cells[i -maxT11Col].style.pixelWidth=wT2;
	 }
      }
     maxWidth= ( wT1 > wT2 ) ? maxWidth + wT1: maxWidth + wT2;
    }

    maxWidth += incr;
    Table1.width= maxWidth;
    Table2.Width= Table1.Width;

    if ( Table1.clientWidth < mainTb.clientWidth ){
       incr= (Div1.style.overflowY=="scroll") ? Table1.clientWidth + 18: Table1.clientWidth;
       adjust= mainTb.clientWidth - incr ;
       adjPerCol= adjust / (maxCol - rb);
       adjPerCol= Math.round(adjPerCol);

     for(k=0;k<n;k++){
       wT1= T1.cells[k].style.pixelWidth;
       T1.cells[k].style.pixelWidth= ( k == (rb-1) ) ? wT1: wT1 + adjPerCol;
       wT2= T2.cells[k].style.pixelWidth;
       T2.cells[k].style.pixelWidth= ( k == (rb-1) ) ? wT2: wT2 + adjPerCol;
     }

     for(k=n;k<maxCol;k++){
      if ( k>=n && k<=(n+maxT11Col) ) {
       wT1= T11.cells[k - n].style.pixelWidth;
       T11.cells[k - n].style.pixelWidth= ( k == (rb-1) ) ? wT1: wT1 + adjPerCol;
      }  else {
       wT1= T1.cells[k - maxT11Col].style.pixelWidth;
       T1.cells[k - maxT11Col].style.pixelWidth= ( k == (rb-1) ) ? wT1: wT1 + adjPerCol; }
      wT2= T2.cells[k].style.pixelWidth;
      T2.cells[k].style.pixelWidth= ( k == (rb-1) ) ? wT2: wT2 + adjPerCol;
     }

    }

 }
  else{ Table1.width="100%" }
}

function divResize(addInfo) {
  var minValue= mainTable.offsetTop + dataDiv1.offsetTop + 30;
  var h = document.body.clientHeight - minValue ;
  var totalrow= parseInt(document.forms[0].totalRow.value);
  var maxHeight= totalrow * 20; //dataDiv1.childNode.offsetHeight;


  if (addInfo) {
    minValue= mainTable.offsetTop + dataDiv1.offsetTop + tbAddInfo.offsetHeight + 4;
  }
  h = (h <= 0) ? maxHeight: h;
  if ( totalrow > 10 && document.body.clientHeight > minValue ) {
     if ( h  < maxHeight ) {
        dataDiv1.style.height= h + "";
   	dataDiv1.style.overflowY="scroll";
       }
    else {
           dataDiv1.style.height=maxHeight + "";
   	     dataDiv1.style.overflowY="";
        }
    }
  else if ( totalrow > 10 && document.body.clientHeight <= minValue ) {
        dataDiv1.style.height= (addInfo) ? "" + tbAddInfo.offsetHeight:"200";
        dataDiv1.style.overflowY="scroll";
   }
  }


 function showChecked(name) {
     var formLength= document.forms[0].elements.length;
     var ok = false;
     for(n=0;n<formLength;n++)
     {
      	var elemt = document.forms[0].elements[n];
      	if(elemt.name == name )
      	{
        		if ( elemt.checked ) {
        		 elemt.click();
             break; 	}
      	}
      }
 }

function showOpt(showall){
 if (showall){
    dataDiv.style.overflowY= "visible";
 } else {
	dataDiv.style.overflowY= "scroll";
	dataDiv.style.height=  mainTable1.offsetTop + mainTable1.offsetHeight + "";
 }
}


function CenterWindow(u,w1,h1,tp) {
   CenterNamedWindow(u,'',w1,h1,tp);
}

function CenterNamedWindow(u,nm,w1,h1,tp)
{
  	var w=screen.width;
	var h=screen.height;
	var posTop=(h-h1)/2;
	var posLeft=(w-w1)/2;
	var position='left='+posLeft+',top='+posTop+',height='+h1+',width='+w1;

	var n =nm
	var X =null;
	var Y = null;
	var cU  =imgPath+'close_up.gif'
	var cO  =imgPath+'close_ovr.gif'
	var cL  =imgPath+'clock.gif'
	var mU  =imgPath+'min_up.gif'
	var mO  =imgPath+'min_ovr.gif'
	var xU  =imgPath+'max_up.gif'
	var xO  =imgPath+'max_ovr.gif'
	var rU  =imgPath+'res_up.gif'
	var rO  =imgPath+'res_ovr.gif'
	var tH  ='<font face=verdana size=1>eIBS - Datapro, Inc.</font>'
	var tW  ='eIBS - Datapro, Inc.'
	var wB  ='#000000'
	var wBs ='#FFFFFF'
	var wBG ='#ABBAD3'
	var wBGs='#ABBAD3'
	var wNS ='toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0'
	var fSO ='scrolling=auto noresize'
	var brd =10;
	var ful =true;
	var min =false;
	var res =true;
	var tsz =20;

	var listin = null;

  switch (tp){
    case 1:
		 listin = window.open(u,nm,position);
		//wNS ='';
		//listin = chromeless(u,n,w1,h1,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz);
		break;
    case 2:
		 listin = window.open(u,nm,'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,'+position);
		//wNS ='toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=yes';
		//listin = chromeless(u,n,w1,h1,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz);
    	break;
    case 3:
		 listin = window.open(u,nm,'scrollbars=yes,'+position);
		//wNS ='toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=yes';
		//listin = chromeless(u,n,w1,h1,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz);
		break;
    case 4:
		 listin = window.open(u,nm,'toolbar=yes,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,'+position);
		//wNS ='toolbar=yes,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=yes';
		//listin = chromeless(u,n,w1,h1,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz);
    	break;
    case 5:
		 listin = window.open(u,nm,'toolbar=no,location=no,directories=no,menubar=no,scrollbars=no,resizable=no,copyhistory=no,'+position);
		//wNS ='toolbar=yes,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0';
		//listin = chromeless(u,n,w1,h1,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz);
    	break;
    case 6:
		 listin = window.open(u,nm,'scrollbars=no,'+position);
		//wNS ='toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=yes';
		//listin = chromeless(u,n,w1,h1,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz);
		break;
    case 7:
		listin = window.open(u,nm,'toolbar=no,location=no,directories=no,menubar=no,scrollbars=no,resizable=yes,copyhistory=no,'+position);
    	break;
    case 8:
		 listin = window.open(u,nm,'window.parent.location,toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,'+position);
		//wNS ='toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=yes';
		//listin = chromeless(u,n,w1,h1,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz);
    	break;
	}


	return listin;


}

function getCodeAcpt(name,cod)
{
	page = prefix + language + "EWD0170_dft_hlp_acpt_container.jsp";
	fieldName = name;
	fieldAux1 = cod;
	CenterWindow(page,600,400,2);
}

function showTRImg(cod)
{
	page= prefix +language + "ECIF030_rt_transaction_img.jsp?imgName=" + cod;
	CenterWindow(page,600,400,2);
}

function showLCInq(cusnum)
{
	page = webapp + "/servlet/datapro.eibs.credit.JSELN0110?SCREEN=400&CUSNUM=" + cusnum;
	CenterWindow(page,600,500,2);
}

function showTransfer(wtnum)
{
	page = webapp + "/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=2&E01SCHNUM=" + wtnum;
	CenterWindow(page,600,500,2);
}

function checksStatus(chk)
{
	page = webapp + "/servlet/datapro.eibs.products.JSECH0565?SCREEN=3&chkNum=" + chk;
	CenterWindow(page,600,500,2);
}

function enterReason(op)
{
	option = op;
	var page= prefix +language + 'ESS0090_message_enter_text.jsp';
	CenterWindow(page,500,430,3);
}

function showMsgViewer() {

	page = prefix + language + "ESS0090_message_container.jsp";
	CenterWindow(page,600,300,2);

}

function showDeductionDetail(code,type,account) {

	page = webapp + "/servlet/datapro.eibs.products.JSEDL0305?SCREEN=2&CODE=" + code + "&TYPE=" + type + "&ACCOUNT="+ account;
	CenterWindow(page,600,500,2);

}

function showInqApproval(app, account, type) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.products.JSEDL0140?SCREEN=3&ACCNUM=" + account + "&appCode=" + app + "&typeCode=" + type;
	CenterWindow(page,600,500,2);

}

function showInqApprovalDDA(app, account,type) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.products.JSEDD1000?SCREEN=3&ACCNUM=" + account + "&appCode=" + app + "&typeCode=" + type;
	CenterWindow(page,600,500,2);

}

function showInqApprovalCL(app, account) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.credit.JSELN0040?SCREEN=3&ACCNUM=" + account + "&appCode=" + app;
	CenterWindow(page,600,500,2);

}

function showInqApprovalClient(account) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.client.JSESD0100?SCREEN=3&ACCNUM=" + account;
	CenterWindow(page,600,500,2);

}

function showInqApprovalColl(app,account) {

var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "REFNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }

	page = webapp + "/servlet/datapro.eibs.client.JSERA0080?SCREEN=3&REFNUM=" + account + "&appCode=" + app;;
	CenterWindow(page,600,500,2);
}


function showInqApprovalForex(app, account, type) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.products.JSEDL9140?SCREEN=3&ACCNUM=" + account + "&appCode=30" + "&typeCode=" + type;
	CenterWindow(page,600,500,2);

}

function showInqApprovalCards(app, account, type, card) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.products.JSECC0140?SCREEN=3&ACCNUM=" + account + "&appCode=" + app + "&typeCode=" + type + "&CARDNUM=" + card;
	CenterWindow(page,600,500,2);

}

function showInqApprovalCC(app, account, type) {
var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == "ACCNUM" && elementValue== account)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.products.JSECC0140?SCREEN=3&ACCNUM=" + account + "&appCode=" + app + "&typeCode=" + type;
	CenterWindow(page,600,500,2);

}

function GetCardAccounts(){
	try{
		var card = document.forms[0].CARD.value;
		window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080?SCREEN=800&E01CCANUM=" + card;
	} catch (e){
		window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080?SCREEN=800";
	}
}

function GetCardAccountsInq(){
	try{
		var card = document.forms[0].CARD.value;
		window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080I?SCREEN=800&E01CCANUM=" + card;
	} catch (e){
		window.location.href="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECC0080I?SCREEN=800";
	}
}

function GetTableFeeCod(name,searcher)
{

	fieldName=name;
    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0412?Search=" + searcher;
	CenterWindow(page,600,100,3);
}

function GetCodeCreditCard(name, flag)
{
	page= webapp + "/servlet/datapro.eibs.helps.JSEWD0410?codeflag=" + flag;
	fieldName=name;
	CenterWindow(page,400,400,1);
}

function showInqAcc(account) {

	page = webapp + "/servlet/datapro.eibs.client.JSECIF010?SCREEN=400&opt=1&ACCNUM=" + account;
	CenterWindow(page,600,500,2);

}

function showInqCollateral(account,apcod) {

	page = webapp + "/servlet/datapro.eibs.client.JSERA0000?SCREEN=4&APPCODE="+ apcod +"&ACCNUM=" + account;
	CenterWindow(page,600,500,2);

}

function goGaBasic() {
 var ref="";
 var client="";


	var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;

      	if(elementName == "REFNUM" && document.forms[0].elements[n].checked)
      	{
        		ref = document.forms[0].elements[n].value;
        		break;
      	}
      }

  if (ref !== ""){

	page = webapp + "/servlet/datapro.eibs.client.JSERA0010?SCREEN=8&REF=" + ref + "&CLIENT=" + client;
	CenterWindow(page,600,500,2);

 }
}


function showProdAcc(app,flg,typ,cde)
{
	flg = "";
	page = webapp + "/servlet/datapro.eibs.client.JSECIF010?SCREEN=9&appCode=" + app + "&flag=" + flg + "&prodType=" + typ + "&prodCode=" + cde;
	CenterWindow(page,600,500,2);
}

function showServices(customer)
{
    page = webapp + "/servlet/datapro.eibs.products.JSECP001?SCREEN=4&CUSTOMER=" + customer;
	CenterWindow(page,530,530,2);

}


function showOffChkApproval(account, currency, purpose) {

	page = webapp + "/servlet/datapro.eibs.products.JSEOF0115A?SCREEN=3&ACCNUM=" + account + "&Currency=" + currency + "&Purpose=" + purpose;
	CenterWindow(page,600,500,2);

}

function getOffClass(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,320,300,2);
}

function showAcc(app,flg)
{
	page = webapp + "/servlet/datapro.eibs.client.JSECIF010?SCREEN=9&appCode=" + app + "&flag=" + flg;
	CenterWindow(page,600,500,2);
}

function showAcc2(app,flg, ofc)
{
	page = webapp + "/servlet/datapro.eibs.client.JSECIF010?SCREEN=9&appCode=" + app + "&flag=" + flg+ "&ofc=" + ofc;
	CenterWindow(page,600,500,2);
}



function showCDRates(ccy,tb)
{
	page = webapp + "/servlet/datapro.eibs.tables.JSECN0030?CCY=" + ccy + "&TABLE=" + tb;
	fieldName=name;
	CenterWindow(page,560,500,2);
}

function showDDAServCharge(bnk,typ,tb)
{
	page = webapp + "/servlet/datapro.eibs.tables.JSESD0712?BANK=" + bnk + "&TYPE=" + typ + "&TABLE=" + tb;
	fieldName=name;
	CenterWindow(page,560,500,2);
}

function showTransfDet(type,num,bank) {

	page =  webapp + "/servlet/datapro.eibs.transfer.JSEDD0610?SCREEN=5&TPY=" + type + "&NMR=" + num + "&BNK=" + bank;
	CenterWindow(page,600,500,2);

}

function showLCServCharge(bnk,typ,tb)
{
	page = webapp + "/servlet/datapro.eibs.tables.JSESD0714?BANK=" + bnk + "&TYPE=" + typ + "&TABLE=" + tb;
	fieldName=name;
	CenterWindow(page,600,500,2);
}

function showLNServCharge(bnk,typ,tb)
{
	page = webapp + "/servlet/datapro.eibs.tables.JSESD0713?BANK=" + bnk + "&TYPE=" + typ + "&TABLE=" + tb;
	fieldName=name;
	CenterWindow(page,600,500,2);
}

function showCOLServCharge(bnk,typ,tb)
{
	page = webapp + "/servlet/datapro.eibs.tables.JSESD0715?BANK=" + bnk + "&TYPE=" + typ + "&TABLE=" + tb;
	fieldName=name;
	CenterWindow(page,600,500,2);
}

function showErrors()
{
	var page= prefix +language + "error_viewer.jsp";
	errorwin = CenterNamedWindow(page,'error',420,300,2);
}

function showWarnings(accnum,name)
{
   var formLength= document.forms[0].elements.length;
   for(n=0;n<formLength;n++)
     {
      	var elementName= document.forms[0].elements[n].name;
      	var elementValue= document.forms[0].elements[n].value;
      	if(elementName == name && elementValue== accnum)
      	{
        		document.forms[0].elements[n].click();
        		break;
      	}
      }
	page = webapp + "/servlet/datapro.eibs.alerts.JSESD0000?ACCNUM=" + accnum;
	CenterWindow(page,420,300,2);
}

function GetCode(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,320,300,3);
}

function lnGetOver(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,400,250,2);

}

function lnGetContr(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,400,300,2);
}

function lnGetFact(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,400,300,2);
}

function GetCodeDesc(name,desc,page)
{
	page= prefix +language + page;
	fieldName=name;
	fieldDesc=desc;
	CenterWindow(page,320,300,2);
}

function GetCodeCNOFC(name, flag)
{
	page= prefix +language + "EWD0002_helpfile_CNOFC.jsp?codeflag=" + flag;
	fieldName=name;
	CenterWindow(page,400,330,2);
}

function GetCodeAuxCNOFC(name, flag, codeaux)
{
	page= prefix +language + "EWD0002_helpfile_CNOFC.jsp?codeflag=" + flag + "&codeaux="+codeaux;
	fieldName=name;
	CenterWindow(page,400,330,2);
}

function GetCodeDescCNOFC(name, desc, flag)
{
	page= prefix +language + "EWD0002_helpfile_CNOFC_Desc.jsp?codeflag=" + flag;
	fieldName=name;
	fieldDesc=desc;
	CenterWindow(page,320,330,2);
}


function GetCNOFCFilteredCodes(name, desc, filter1, flag)
{
  	fieldName=name;
	fieldDesc=desc;
	var filter=filter1;
    page= prefix +language + "EWD0002_helpfile_CNOFC_DescF.jsp?filter=EWDMID&EWDMID="+filter+"&codeflag=" + flag;
	//alert (page);
	CenterWindow(page,450,300,2);

}

function GetCodeDescAuxCNOFC(name, desc, flag, codeaux)
{
page= prefix +language + "EWD0002_helpfile_CNOFC_Desc.jsp?codeflag=" + flag + "&codeaux="+codeaux;
fieldName=name;
fieldDesc=desc;
CenterWindow(page,320,330,2);
}

//GetParroquiaCodes
function GetParroquiaCodes(name, desc, filter1, filter2,flag)
{

	fieldName=name;
	fieldDesc=desc;
	var filter="";
	//alert(filter1+ " , "+ filter2);
	if (filter1 != "")
	  filter=filter1;
	else
	  filter=filter2;
	page= prefix +language + "EWD0002_helpfile_CNOFC_DescF.jsp?filter=EWDMID&EWDMID="+filter+"&codeflag=" + flag;
	//alert (page);
	CenterWindow(page,450,300,2);
}
//GetPanamaAddressCodes cnofc
function Get2FilterCodes(name, desc, flag, filter1, filter2)
{

	fieldName=name;
	fieldDesc=desc;
	var f1=filter1;
	var f2=filter2;
	//alert(filter1+ " , "+ filter2);
	page= prefix +language + "EWD0002_helpfile_CNOFC_DescF.jsp?filter=EWDMID&filter=EWDMIC&EWDMID="+f1+"&EWDMIC=" + f2+"&codeflag=" + flag;
	//alert (page);
	CenterWindow(page,450,300,2);
}

// Used only to CorpBanca
function GetCodeDescCNOFC1(name, desc,name1, desc1, flag)
{
	page= prefix +language + "EWD0152_helpfile_CNOFC.jsp?codeflag=" + flag;
	fieldName=name;
	fieldDesc=desc;
	fieldAux1=name1;
	fieldAux2=desc1;
	CenterWindow(page,450,300,2);
}

// Used only to CorpBanca
function GetCodeDescDeal(name, desc)
{
	page= prefix +language + "EWD0158_Deal_Table.jsp";
	fieldName=name;
	fieldDesc=desc;
	CenterWindow(page,450,300,2);
}

function GetCustomer(name)
{
	GetCustomerDescId(name, '', '');
}

function GetCustomerDetails(customer,name,idnumber,idtype,idcountry,address2,address3,address4,city,state,country,pob,zip,citizen,prof,gender,marital,phone){

    page= prefix + language + "EWD0001_client_desc_details_help_container.jsp";
	fieldAux1=customer;
	fieldAux2=name;
	fieldAux3=idnumber;
	fieldAux4=idtype;
	fieldAux5=idcountry;
	fieldAux6=address2;
	fieldAux7=address3;
	fieldAux8=address4;
	fieldAux9=city;
	fieldAux10=state;
	fieldAux11=country;
	fieldAux12=pob;
	fieldAux13=zip;
	fieldAux14=citizen;
	fieldAux15=prof;
	fieldAux16=gender;
	fieldAux17=marital;
	fieldAux18=phone;

	CenterWindow(page,600,400,3);
}

function GetCustomerDetails2(cus,nme,idn,idt,idc,ctr,ste,rgn,mun,cty,par,pob,urb,str,hom,flr,apt,ref,zip,ctz,prf,gnd,mst,phn){

    page= prefix + language + "EWD0700_customer_details_help_container.jsp";
	fieldAux1=cus;
	fieldAux2=nme;
	fieldAux3=idn;
	fieldAux4=idt;
	fieldAux5=idc;
	fieldAux6=ctr;
	fieldAux7=ste;
	fieldAux8=rgn;
	fieldAux9=mun;
	fieldAux10=cty;
	fieldAux11=par;
	fieldAux12=pob;
	fieldAux13=urb;
	fieldAux14=str;
	fieldAux15=hom;
	fieldAux16=flr;
	fieldAux17=apt;
	fieldAux18=ref;
	fieldAux19=zip;
	fieldAux20=ctz;
	fieldAux21=prf;
	fieldAux22=gnd;
	fieldAux23=mst;
	fieldAux24=phn;

	CenterWindow(page,700,400,3);
}

function GetCustomerDetailsLC(customer,name,address2,address3,address4,state,zip,pais,type){

    page= prefix + language + "EWD0001_client_lc_help_container.jsp";
	fieldAux1=customer;
	fieldAux2=name;
	fieldAux3=address2;
	fieldAux4=address3;
	fieldAux5=address4;
	fieldAux6=state;
	fieldAux7=zip;
	fieldAux8=pais;
	fieldAux9=type;

	CenterWindow(page,600,400,3);
}

function GetCustomerId(name)
{
	GetCustomerDescId('', '', name);
}

function GetVendor(name)
{
	page= prefix + language + "EWD0030_vendor_help_container.jsp";
	fieldName=name;
	CenterWindow(page,500,500,1);
}

function GetVendor(name, codeflag)
{
	page= prefix + language + "EWD0030_vendor_help_container.jsp?codeflag=" + codeflag;
	fieldName=name;
	CenterWindow(page,500,500,1);
}

function GetInternetVendor(name, codeflag)
{
	page= prefix + language + "EWD0705_vendor_help_container.jsp?codeflag=" + codeflag;
	fieldName=name;
	CenterWindow(page,500,500,1);
}

function GetOfficer(name)
{
	page= prefix + language + "EWD0122_officer_help_container.jsp";
	fieldName=name;
	CenterWindow(page,500,500,1);
}

function getOficial(cod,branch)
{
	if (branch == "") branch = "999";
	fieldCod=cod;
    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0123?Branch=" + branch;
	CenterWindow(page,500,350,2);

}

function GetCustomerDescId(name, desc, id)
{
	page= prefix + language + "EWD0001_client_desc_id_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	fieldId=id;
	fieldAux1="";
	CenterWindow(page,530,530,1);
}

function GetCustomerDescId2(name, desc, id)
{
	page= prefix + language + "EWD0001B_client_desc_id_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	fieldId=id;
	fieldAux1="";
	CenterWindow(page,530,560,1);
}

function GetCorrespondentDescId(name, desc, id)
{
	page= prefix + language + "EWD0505_correspondent_desc_id_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	fieldId=id;
	fieldAux1="";
	CenterWindow(page,530,530,1);
}
function GetCorrespondentDescIdSwift(name, desc, id)
{
	page= prefix + language + "EWD0545_correspondent_desc_id_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	fieldId=id;
	fieldAux1="";
	CenterWindow(page,530,530,1);
}

function GetCustomerCorp(name, desc, id)
{
	page= prefix + language + "EWD0001_client_desc_id_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	fieldId=id;
	fieldAux1="1";
	CenterWindow(page,530,530,1);
}

function GetCustomerDescIdW(name, desc, id)
{
	page= prefix + language + "EWD0001W_client_desc_id_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	fieldId=id;
	CenterWindow(page,530,530,1);
}



function SayThis(s)
{
	applet=document.applets.Speaker;
	s = prefix + language + "media/" + s;
	if(applet!=null)
		document.applets["Speaker"].SayThis(s)
}



function GetAccount(name,bnk,app,sel)
{
   page= prefix +language + "EWD0005_client_help_container.jsp";
   fieldName=name;
   fieldDesc="";
   fieldAux1="";
   fieldAux2="";
   fieldAux3="";
   fieldAux4="";
   AppCode = app;
   Bank = bnk;
   Selection=sel;
   CenterWindow(page,600,450,1);
}

function GetAccountInfo(name,bnk,app,sel,id,cust,ccy,type,prod)
{
   page= prefix +language + "EWD0005_client_help_container.jsp";
   fieldName=name;
   fieldDesc=cust;
   fieldAux1=id;
   fieldAux2=ccy;
   fieldAux3=type;
   fieldAux4=prod;
   AppCode = app;
   Bank = bnk;
   Selection=sel;
   CenterWindow(page,600,450,1);
}

function GetOldAccount(bnk,brn,ccy,aty,cun,oac,acc)
{
   Bank = bnk;
   Branch = brn;
   Currency = ccy;
   PrdType = aty;
   OldAcc = oac;
   NewAcc = acc,
   Custumer = cun;
   page= prefix +language + "EWD0406_old_acc_help_container.jsp";
   CenterWindow(page,600,450,1);
}


function GetAccByClient(name,bnk,app,sel,client)
{
   page= prefix +language + "EWD0005_client_help_container.jsp";
   fieldName=name;
   fieldDesc="";
   fieldAux1="";
   fieldAux2="";
   fieldAux3="";
   fieldAux4="";
   AppCode = app;
   Bank = bnk;
   Selection=sel;
   Client=client;
   CenterWindow(page,600,450,1);
}

function GetLNReference(name,bnk,app,sel,client)
{
   GetAccByClient(name,bnk,app,sel,client);
}

function GetAccountByType(name,bnk,app,sel)
{
   page= prefix +language + "EWD0005_account_help_container.jsp";
   fieldName=name;
   AppCode = app;
   Bank = bnk;
   Selection=sel;
   CenterWindow(page,600,450,1);
}

function GetAcceptantId(name)
{
	page= prefix +language + "EWD0140_acceptant_help_container.jsp" ;
	fieldName=name;
	fieldAux1= 'none';
	CenterWindow(page,530,450,1);
}

function GetAcceptantIdNDA(name,ndaname)
{
	page= prefix +language + "EWD0140_acceptant_help_container.jsp" ;
	fieldName=name;
	fieldAux1= ndaname;
	CenterWindow(page,530,450,1);
}

function GetProduct(name,app,bank,desc,type)
{
	page= prefix +language + "EWD0008_client_help_container.jsp"
	fieldName=name;
	fieldAux1=desc;
	fieldAux2=type;
	AppCode = app;
	ProductBank = bank;
	CenterWindow(page,600,400,1);
}

function GetLedgerOrAccount(name,bnk,ccy,apc,sel)
{
   Bank = trim(bnk);
   Currency = trim(ccy);
   fieldName =name;
   AppCode = apc;
   Selection = sel;


   if ((Bank.length > 0) && (Currency.length > 0)){
     AppCode = "";
     GetLedger(fieldName,Bank,Currency,AppCode);
     }
   else {
     var formLength= document.forms[0].elements.length;
     for(n=0;n<formLength;n++){
     var elementName= document.forms[0].elements[n].name;
	if(elementName == fieldName){
  		document.forms[0].elements[n-1].value = "";
  		document.forms[0].elements[n-3].value = "";
  		break;
	}
     }
     Bank = "";
     GetAccount(fieldName,Bank,AppCode,Selection);

     }

}

function GetLedger(name,bnk,ccy,apc,desc)
{
   page= prefix +language + "EWD0010_client_help_container.jsp";
   fieldName=name;
   fieldDesc=desc;
   AppCode = apc;
   Bank = bnk;
   Currency=ccy;
   CenterWindow(page,600,450,1);


}

function GetCurrency(name,bank)
{
	fieldName=name;
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0012?BankNumber=" + bank;
	CenterWindow(page,500,250,2);

}
function GetBankAch(name,codi)
{
	fieldName=name;
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0720?COD=" + codi;
	CenterWindow(page,500,250,2);

}
function GetTrnAch(name,codi)
{
	fieldName=name;
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0720?SCREEN=2&COD=" + codi;
	CenterWindow(page,500,250,2);

}

function GetCurrencyDesc(name,desc,bank)
{
	fieldName=name;
	fieldDesc=desc;
    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0012?SCREEN=2&BankNumber=" + bank;
	CenterWindow(page,500,250,2);

}

function GetInstCurrency(name,bank,inst)
{
	fieldName=name;
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0500?BankNumber=" + bank + "&Inst=" + inst;
	CenterWindow(page,500,350,2);

}


function GetCheckForeignExc(sub,name,lot,ini,fin,bal,chk,bank,branch,type,user)
{
	fieldSub=sub;
	fieldName=name;
	fieldLot=lot;
	fieldIni=ini;
	fieldFin=fin;
	fieldBal=bal;
	fieldChk=chk;
    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0510?Bank=" + bank + "&Branch=" + branch + "&Type=" + type + "&User=" + user;
	CenterWindow(page,500,350,2);

}


function GetValuePaper(lot,bal,ini,fin,bank,branch,type,sub)
{
	fieldLot=lot;
	fieldBal=bal;
	fieldIni=ini;
	fieldFin=fin;
    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0535?Bank=" + bank + "&Branch=" + branch + "&Type=" + type + "&Sub=" + sub;
	CenterWindow(page,500,350,2);

}


function GetBranch(name,bank,desc)
{

	fieldName=name;
	fieldDesc=desc;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0013?BankNumber=" + bank;
	CenterWindow(page,600,270,2);
}

function GetBatchDet(date1,date2,date3,batch,acr)
{
	var param="&DT1="+date1+"&DT2="+date2+"&DT3="+date3+"&BTH="+batch+"&ACR="+acr+"&Pos=0";
	page = webapp + "/servlet/datapro.eibs.client.JSEGL0421?SCREEN=1" + param;
	CenterWindow(page,700,500,2);
}

function GetBudget(name,bank,currency)
{
	page= prefix +language + "EWD0011_client_help_container.jsp"
	fieldName=name;
	BankNumber = bank;
	Currency = currency;
	CenterWindow(page,500,200,1);

}

function GetSwiftId(name)
{
	page= prefix +language + "EWD0130_bankid_help_container.jsp"
	fieldName=name;
	PVia="S";
	CenterWindow(page,530,450,1);

}

function GetFedId(name)
{
	page= prefix +language + "EWD0130_bankid_help_container.jsp"
	fieldName=name;
	PVia="F";
	CenterWindow(page,530,450,1);

}

function GetIssuerCode(name,type)
{
	page= prefix +language + "EWD0301_issuer_help_container.jsp"
	fieldName=name;
	fieldId=type;
	CenterWindow(page,530,450,1);
}

function GetPortfolioNum(name,type)
{
	page= prefix +language + "EWD0302_portfolio_help_container.jsp"
	fieldName=name;
	fieldId=type;
	CenterWindow(page,530,450,1);
}

function GetCommTable(name,bank)
{
	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0303?Bank=" + bank;
	CenterWindow(page,400,280,1);
}

function GetDocumentType(name,type)
{
	page= prefix +language + "EWD0304_doc_help_container.jsp"
	fieldName=name;
	fieldId=type;
	CenterWindow(page,530,450,1);
}

function GetDocument(name, account, id, status)
{
   	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0145?Account=" + account + "&id=" + id + "&Status=" + status;
   	CenterWindow(page,600,450,1);
}

function GetCostCenter(name,bank)
{

	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0014?BankNumber=" + bank;
	CenterWindow(page,400,280,3);
}

function GetCreditLine(name,custnum)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0015?CustNum=" + custnum;
	fieldName=name;
	CenterWindow(page,630,90,3);

}

function GetSubAcc(name,account)
{
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0016?AccountNum=" + account;
	fieldName=name;
	CenterWindow(page,500,100,1);
}

function GetAct(name)
{
	page= prefix +language + "EWD0020_client_help_helpmessage.jsp"
	fieldName=name;
	CenterWindow(page,480,170,3);
}

function GetLoc(name)
{
	page= prefix +language + "EWD0021_client_help_helpmessage.jsp" ;
	fieldName=name;
	CenterWindow(page,350,170,3);
}

function GetDocLC(name,level)
{
   var LevelNum = "?Level=" + level ;
	page= prefix +language + "EWD0022_client_help_helpmessage.jsp"+ LevelNum;
	fieldName=name;
	CenterWindow(page,600,170,3);
}

function GetMailing(name,custnum)
{
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0026?CustNum=" + custnum;
	fieldName=name;
	CenterWindow(page,450,350,3);
}

function GetTellTran(name)
{
	page= prefix +language + "EWD0029_teller_code_transac_helpmessage.jsp" ;
	fieldName=name;
	CenterWindow(page,600,400,3);
}


function GetRateTable(name)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0032";
	fieldName=name;
	CenterWindow(page,430,200,3);
}

function GetBanksSuc(bank, suc)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0702";
	fieldName=bank;
	fieldDesc=suc;
	
	CenterWindow(page,430,200,3);
}



function GetCNTRLPRF(name,amt)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0108";
	fieldName=name;
	fieldDesc=amt;
	CenterWindow(page,430,200,3);
}

function GetOvernightTable(name, bnk)
{
	// page = webapp + "/servlet/datapro.eibs.helps.JSEWD0045?BANK=" + bnk;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0045";
	fieldName=name;
	CenterWindow(page,430,200,3);
}

function GetFloating(name)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0100";
	fieldName=name;
	CenterWindow(page,600,140,3);
}

function GetTariffLC(name,searcher,EWDCUN)
{

	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0101?Search=" + searcher + "&EWDCUN=" + EWDCUN;
	CenterWindow(page,600,150,3);
}

function GetRetCod(name,searcher)
{

	fieldName=name;
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0102?Search=" + searcher;
	CenterWindow(page,600,100,3);
}


function GetTariffColl(name,searcher,EWDCUN)
{

	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0104?Search=" + searcher + "&EWDCUN=" + EWDCUN;
	CenterWindow(page,600,150,3);
}

function GetLoanTable(name,searcher)
{

	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0105?Search=" + searcher;
	CenterWindow(page,600,100,3);
}


function GetPrevTable(name)
{

        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0106";
	fieldName=name;
	CenterWindow(page,600,240,3);
}

function GetCasualTable(name, desc)
{

	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0110";
	fieldName=name;
	fieldDesc=desc;
	CenterWindow(page,600,400,3);
}


function GetForExcFee(name)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0111";
	fieldName=name;
	CenterWindow(page,600,100,3);
}

function GetForExcPar(name)
{
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0112";
	fieldName=name;
	CenterWindow(page,600,100,3);
}

function GetInsPar(name)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0113";
	fieldName=name;
	CenterWindow(page,600,200,3);
}

function GetOffChkPar(name)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0115";
	fieldName=name;
	CenterWindow(page,600,250,3);
}

function GetConcept(name,bank,app,desc,ledger)
{
	var AppCode = "?App=" + app + "&Bank=" + bank;
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0035" + AppCode;
	fieldName=name;
	fieldAux1=desc;
	fieldAux2=ledger;
	CenterWindow(page,500,230,3);
}

function GetProductRates(name,app)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0002P?App=" + app;
	fieldName=name;
	CenterWindow(page,500,230,3);
}


function GetTypCHK(name,desc,prodtyp,ccy, ccy2,namccy)
{
if (ccy2 == null){
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0118?SCREEN=3&PRODTYP="+prodtyp+"&CCY="+ccy;
}else {
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0118?SCREEN=3&PRODTYP="+prodtyp+"&CCY="+ccy+"&CCY2="+ccy2;
}
	fieldName=name;
	fieldDesc=desc;
	fieldAux1=namccy;
	CenterWindow(page,500,250,3);
}

function GetTypCHKBook(name,hideF1,hideF2,desc,prodtyp,ccy)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0118?SCREEN=3&PRODTYP="+prodtyp+"&CCY="+ccy;
	fieldName=name;
	fieldAux1=hideF1;
	fieldAux2=hideF2;
	fieldDesc=desc;
	CenterWindow(page,500,250,3);
}

function GetCallReport(name,desc)
{
	page= prefix +language + "EWD0091_client_help_container.jsp";
	fieldName=name;
	fieldDesc=desc;
	CenterWindow(page,530,450,3);
}

function GetCheck(name, type, status)
{
	page = webapp + "/servlet/datapro.eibs.products.JSETL0510?SCREEN=1000&E01SELDTY="+type+"&E01SELSCH="+status;
	fieldName=name;
	CenterWindow(page,640,500,3);
}

function GetCheckByAccount(name, account, type, status)
{
	page = webapp + "/servlet/datapro.eibs.products.JSETL0510?SCREEN=1000&E01SELACC="+account+"&E01SELDTY="+type+"&E01SELSCH="+status;
	fieldName=name;
	fieldAccount="";
	CenterWindow(page,640,500,3);
}

function GetCheckAccount(check, account, type, status)
{
	page = webapp + "/servlet/datapro.eibs.products.JSETL0510?SCREEN=1000&E01SELDTY="+type+"&E01SELSCH="+status;
	fieldName=check;
	fieldAccount=account;
	CenterWindow(page,640,500,3);
}

function GetBroker(name)
{
   page= prefix +language + "EWD0023_brkr_help_container.jsp";
   fieldName=name;
   CenterWindow(page,600,500,1);
}


function GetBrokerI(name,desc){

	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0306B";
	fieldName=name;
	fieldAux1=desc;
	CenterWindow(page,500,230,3);

}

function GetTypeBroker(name,type)
{
   page= prefix +language + "EWD0023_brkr_help_container.jsp";
   fieldName=name;
   fieldAux1=type;
   CenterWindow(page,600,500,1);
}

function GetCredCond(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,420,300,2);
}

function GetRel1(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,420,300,2);
}

function GetRel2(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,350,150,2);
}

function GetClassCred(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,420,300,2);
}

function showDedMaint() {

	var page= prefix +language + 'EDL0150_ln_ded_det.jsp';
	CenterWindow(page,600,300,2);

}

function showCollMaint(client) {

	var page= prefix +language + 'EDL0150_ln_coll_det.jsp?client='+client;
	CenterWindow(page,600,500,2);
}


function showPayMaint() {

	var page= prefix +language + 'EDL0150_ln_pay_pln_det.jsp';
	CenterWindow(page,600,500,2);

}

function showIrregPayMaint() {

	var page= prefix +language + 'EDL0150_ln_pay_pln_irreg.jsp';
	CenterWindow(page,600,500,2);

}

function showCDPayMaint() {

	page = webapp + "/servlet/datapro.eibs.products.JSEXEDL0130?SCREEN=9";
	CenterWindow(page,600,500,2);

}

function GetDepTyp(name,page)
{
	page= prefix +language + page;
	fieldName=name;
	CenterWindow(page,320,300,2);
}

function GetAccountHolders(name)
{
	page= prefix +language + "EWD0002_helpfile_CNOFC.jsp?codeflag=T8"; 
	fieldName=name;
	CenterWindow(page,340,400,2);
}

function MM_swapImgRestore() {// v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() {// v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) {// v3.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document); return x;
}

function MM_swapImage() {// v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}


function GetStatDesc(App, Desc, Dte, TC)
{
	var AppCode = "?AppCode=" + App + "&Desc=" + Desc + "&Dte=" + Dte + "&TC=" + TC ;
	page= prefix +language + "EWD0040_st_add_desc.jsp" + AppCode;
	fieldName=name;
	CenterWindow(page,500,200,3);
}

function showQuoteDetail(rowNum)
{
	page= prefix +language + "EDL0900_ln_crn_pay_det.jsp?Row=" + rowNum;
	CenterWindow(page,600,500,3);
}

function showDocDesc() {

	page = webapp + "/servlet/datapro.eibs.trade.JSELC0455?SCREEN=2";
	CenterWindow(page,600,500,2);

}

function showDocDet(bank, branch, currency, amount, check) {

	page =  webapp + "/servlet/datapro.eibs.products.JSETL0510?SCREEN=3&BNK=" + bank + "&BRN=" + branch + "&CCY=" + currency + "&AMT=" + amount + "&CHK=" + check;
	CenterWindow(page,600,500,2);

}

function showChkDet(bank, branch, currency, amount, check) {

	page =  webapp + "/servlet/datapro.eibs.products.JSETL0510?SCREEN=7&BNK=" + bank + "&BRN=" + branch + "&CCY=" + currency + "&AMT=" + amount + "&CHK=" + check;
	CenterWindow(page,600,500,2);

}

function showChkCanDet(bank, branch, currency, amount, check) {

	page =  webapp + "/servlet/datapro.eibs.products.JSETL0510?SCREEN=13&E02OFMBNK" + bank + "&E02OFMBRN=" + branch + "&E02OFMCCY=" + currency + "&E02OFMMCH=" + amount + "&E02OFMNCH=" + check;
	CenterWindow(page,600,500,2);

}

function showDocDesC() {

	page = webapp + "/servlet/datapro.eibs.trade.JSELC0455C?SCREEN=2";
	CenterWindow(page,600,500,2);

}

function GetBuySalRef(name,typ){

    page = webapp + "/servlet/datapro.eibs.helps.JSETL0510H";
	fieldName=name;
	fieldAux1=typ;
	fieldAux2=desc;
	CenterWindow(page,500,230,3);
}


function showMantDiv(account) {

	page =  webapp + "/servlet/datapro.eibs.products.JSETL0510H?SCREEN=3&E01EFEACC=" + account;
	window.location.href = page;


}

function showInqDiv(account) {

	page =  webapp + "/servlet/datapro.eibs.products.JSETL0510H?SCREEN=7&E01EFEACC=" + account;
	CenterWindow(page,600,500,2);

}

function goOpenerAction(opt) {
var myopener = top.opener.document;

 top.close();
 if ( opt=="A" ) {
   myopener.anchors("linkApproval").click();
 } else if (opt=="J") {
   myopener.anchors("linkJ").click();
 } else if (opt=="K") {
   myopener.anchors("linkK").click();
 }else if (opt=="D") {
   myopener.anchors("linkDelete").click();
 }else if (opt=="Z") {
   myopener.forms[0].submit();
 } else {
   myopener.anchors("linkReject").click();
 }
}

function imgDown(name, img_over)  {
      MM_preloadImages(imgPath +language + img_over);
      MM_swapImage(name,'',imgPath +language + img_over,1);

      // alert("name=" + name + " --- image=" + imgPath +language + img_over);

      // setTimeout('document.forms[0].submit()','1000');
      // delay here and
      // MM_swapImgRestore();
}


 function autotab(e){

 var elem;
	if( isIE() ){
        keyCode = event.keyCode;
		elem=event.srcElement;
	} else {
        keyCode = e.which;
        elem = e.target;
	}

 var coll=document.forms[0];
 var i=0;
  if (keyCode==9 || keyCode==16) return; //TAB=9 and SHIFT=16
  if (elem.type=="text" || elem.type=="password") {
   if (elem.value.length > (elem.maxLength-1)){
      while (i<coll.length){
        if (coll[i]==elem) { break;} else {i++;}
       }
      i++;
      while (i < coll.length) {
        if (coll[i].type=="hidden" || coll[i].readOnly || coll[i].style.visibilty =="hidden") {i++;}
        else {
         coll[i].focus();
         if (coll[i].tagName !=="SELECT") { coll[i].select();}
         break;}
       }

   }
 }
 }

 document.onkeyup=autotab;

  function isIE(){
  	if( document.all ){
		// IE
  		return true;
  	}
  }

 function getMSIEVer(){
  var ver=0;
  var start= (navigator.appVersion.indexOf("MSIE ") != -1) ? navigator.appVersion.indexOf("MSIE ") : 0;
  var end= (start > 0) ? navigator.appVersion.indexOf(";",start) : 0;

  ver= parseFloat(navigator.appVersion.substring(start + 5,end)); // +5 because length("MSIE ")=5
  return(ver);

 }

if ( getMSIEVer() < 5.5 ) {
  var tempStyle = "<STYLE>"+
  		  ".TDADDINFO{ filter:BlendTrans(duration=1);}"+
		  "</STYLE>"
  document.write(tempStyle);
}

// date validation .

function getActualDate(DTFMT, firstDay){
var now = new Date();
var year  = now.getYear();
var month  = now.getMonth()+1;
if (firstDay) var day = "01";
else var day  = now.getDate();
var today = '';
if (DTFMT == 'MDY') today = month+'/'+day+'/'+year;
else if (DTFMT == 'DMY') today = day+'/'+month+'/'+year;
     else
         if (DTFMT == 'YMD') today = year+'/'+day+'/'+month;

return today;
}

//////// Check whether string s is empty.
function isEmpty(s)
{   return ((s == null) || (s.length == 0))
}

// isYear (STRING s)
//
// isYear returns true if string s is a valid
// Year number.  Must be 2 or 4 digits only.
//
function isYear (s)
{   if (isEmpty(s)) return false;
    return ((s.length == 2) || (s.length == 4));
}

// isMonth (STRING s)
//
// isMonth returns true if string s is a valid
// month number between 1 and 12.
//
function isMonth (s)
{   if (isEmpty(s)) return false;
    return (parseInt(s,10) >=1 && parseInt(s,10) <=12);
}

// isDay (STRING s)
//
// isDay returns true if string s is a valid
// day number between 1 and 31.
//
function isDay (s)
{   if (isEmpty(s)) return false;
    return (parseInt(s,10) >=1 && parseInt(s,10) <=31);
}

// daysInFebruary (INTEGER year)
//
// Given integer argument year,
// returns number of days in February of that year.
function daysInFebruary (year)
{   // February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (  ((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0) ) ) ? 29 : 28 );
}


//validate date
function isDate (year, month, day)
{
    if (! (isYear(year) && isMonth(month) && isDay(day)) ) return false;
    var intYear = parseInt(year,10);
    var intMonth = parseInt(month,10);
    var intDay = parseInt(day,10);
    if ((intMonth == 2) && (intDay > daysInFebruary(intYear))) return false;
    return true;
}

//format date ,fills day & month with leading zeroes, and year with century
function fDate(vDate, fmtDate){
 var dateArray=vDate.split("/");
 var day = "";
 var month = "";
 var year = "";
 if (fmtDate == "MDY") {
    day = dateArray[1];
    month = dateArray[0];
    year = dateArray[2];
 } else if (fmtDate == "DMY") {
           day = dateArray[0];
           month = dateArray[1];
           year = dateArray[2];
	} else if (fmtDate == "YMD") {
                  day = dateArray[2];
                  month = dateArray[1];
                  year = dateArray[0];}
                else return "";
day = ((day.length == 1)?"0"+day:day);
month = ((month.length == 1)?"0"+month:month);
year = ((year.length == 2)? ((parseInt(year,10)>50)?"19":"20") + year:year);
if (fmtDate == "MDY") return (month+'/'+day+'/'+year);
else if (fmtDate == "DMY") return (day+'/'+month+'/'+year);
     else if (fmtDate == "YMD") return (year+'/'+month+'/'+day);
          else return "";
}

//yy/mm/dd
function isDateValid (year, month, day)
{
    day = ((day.length == 1)?"0"+day:day);
    month = ((month.length == 1)?"0"+month:month);
    year = ((year.length == 2)? ((parseInt(year,10)>50)?"19":"20") + year:year);

    return isDate(year,month,day);
}

// disable click right
function right() {
 if (navigator.appName == 'Microsoft Internet Explorer' &&
(event.button == 2 || event.button == 3)) {
   if (!(event.srcElement.type=="text")){
	alert("eIBS \nDatapro, inc.\n" + msgRightClick);
	return false;}
}
return true;
}

function closeError() {

 if (errorwin !== null) {
   if (errorwin !== window)
	errorwin.close();
 }

}

// document.onmousedown=right;
function RestoreFilter(){
  var tabcoll=document.all.tags("TABLE");
  if (tabcoll!=null)
	{
    for (i=0; i<tabcoll.length; i++)
      if (tabcoll[i].className=="tableinfo") tabcoll[i].style.filter="progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true')";
  }

}

function RemoveFilter(){
  var tabcoll=document.all.tags("TABLE");
  if (tabcoll!=null)
	{
    for (i=0; i<tabcoll.length; i++)
      if (tabcoll[i].className=="tableinfo") tabcoll[i].style.filter="";
  }
}

window.onafterprint=RestoreFilter;
window.onbeforeprint=RemoveFilter;
window.onunload=closeError;

/*
	CHROMELESS WINDOWS v.35.1 [ 8.1K ]
	Licensed under GNU LGPL (www.gnu.org)

	(c) Gabriel Suchowolski,2000 >> www.microbians.com
	Thanks to Gustavo Ponce >> www.urbanlove.org (resize addon)
*/

function chromeless(u,n,W,H,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,max,min,res,tsz){
	var c=(document.all&&navigator.userAgent.indexOf("Win")!=-1)?1:0
	var v=navigator.appVersion.substring(navigator.appVersion.indexOf("MSIE ")+5,navigator.appVersion.indexOf("MSIE ")+8)
	min=(v>=5.5?min:false);
	var w=window.screen.width; var h=window.screen.height
	var W=W||w; W=(typeof(W)=='string'?Math.ceil(parseInt(W)*w/100):W); W+=(brd*2+2)*c
	var H=H||h; H=(typeof(H)=='string'?Math.ceil(parseInt(H)*h/100):H); H+=(tsz+brd+2)*c
	var X=X||Math.ceil((w-W)/2)
	var Y=Y||Math.ceil((h-H)/2)
	var s=",width="+W+",height="+H

	if(c){
		var cTIT='\n'+
		'<html><head><META HTTP-EQUIV="imagetoolbar" CONTENT="no">\n'+
		'<script>\n'+
		'var IcU=new Image();IcU.src="'+cU+'";var IcO=new Image();IcO.src="'+cO+'";var IcL=new Image();IcL.src="'+cL+'";var IxU=new Image();IxU.src="'+xU+'";var IxO=new Image();IxO.src="'+xO+'";var IrU=new Image();IrU.src="'+rU+'";var IrO=new Image();IrO.src="'+rO+'";var ImU=new Image();ImU.src="'+mU+'";var ImO=new Image();ImO.src="'+mO+'"\n'+
		'document.onmousemove=document.onselectstart=document.ondragstart=document.oncontextmenu=new Function("wMOV();return false");\n'+
		'b=-1\n'+
		'wLOA=function(){if(top.ok&&document.body){'+(min?'bMIN.style.visibility="visible";':'')+'bLOA.style.visibility="hidden";wRSZ()}else setTimeout("wLOA()",500)};wLOA()\n'+
		'wRSZ=function(){var dw=document.body.clientWidth;bCLO.style.pixelLeft=dw-22;bMIN.style.pixelLeft=bLOA.style.pixelLeft=dw-62;bFUL.style.pixelLeft=bRES.style.pixelLeft=dw-42}\n'+
		'wMAX=function(m){top.mod=m;if(m){top.mT(0,0);top.rT('+w+','+h+');bFUL.style.visibility="hidden";bRES.style.visibility="visible"}else{top.mT(top.px,top.py);top.rT(top.sW,top.sH);bFUL.style.visibility="visible";bRES.style.visibility="hidden"}}\n'+
		'wDBL=function(){if(!top.mod)wMAX(1);else wMAX(0)}\n'+
		'wMIN=function(){top.window.moveTo(0,-4000);if(top.opener&&!top.opener.closed){top.opener.window.focus()};top.window.blur()}\n'+
		'wMOV=function(){\n'+
		'if(b==0){top.bCOL("'+wBG+'","'+wB+'");b=-1}\n'+
		'if(b==2&&!top.mod){top.px=event.screenX-ofx-1;top.py=event.screenY-ofy-1;top.mT(top.px,top.py)}\n'+
		'if(b==1){top.bCOL("'+wBGs+'","'+wBs+'");ofx=event.x;ofy=event.y;b=2}\n'+
		'}</script></head>\n'+
		'<body onresize="wRSZ()" bgcolor='+wBG+'>\n'+
		'<div style="position:absolute;left:5px;top:4px;width:2000px">'+tH+'</div>\n'+
		'<img id=bMOV style="position:absolute;left:-50px;top:-50px" '+(max?'ondblclick="wDBL()"':'')+' onmousemove="wMOV()" onmousedown="b=1;wMOV()" onmouseup="b=0;wMOV()" border=0 src="" width=2000 height=2000>\n'+
		'<img id=bFUL style="position:absolute;top:4px;left:'+(W-42)+'px;'+(max?'':'display:none')+'" src="'+xU+'" border=0 width=11 height=11 onmouseover="this.src=IxO.src" onmouseout="this.src=IxU.src" onmouseup="this.src=IxU.src" onmousedown="this.src=IxU.src" onclick="wMAX(1)">\n'+
		'<img id=bRES style="position:absolute;top:4px;left:'+(W-42)+'px;visibility:hidden" src="'+rU+'" border=0 width=11 height=11 onmouseover="this.src=IrO.src" onmouseout="this.src=IrU.src" onmouseup="this.src=IrU.src" onmousedown="this.src=IrU.src" onclick="wMAX(0)">\n'+
		'<img id=bCLO style="position:absolute;top:4px;left:'+(W-22)+'px;" src="'+cU+'" border=0 width=11 height=11 onmouseover="this.src=IcO.src" onmouseout="this.src=IcU.src" onmouseup="this.src=IcU.src" onmousedown="this.src=IcU.src" onclick="top.window.close()">\n'+
		'<img id=bLOA style="position:absolute;top:4px;left:'+(W-62)+'px;" src="'+cL+'" border=0 width=11 height=11>\n'+
		'<img id=bMIN style="position:absolute;top:4px;left:'+(W-62)+'px;visibility:hidden" src="'+mU+'" border=0 width=11 height=11 onmouseover="this.src=ImO.src" onmouseout="this.src=ImU.src" onmouseup="this.src=ImU.src" onmousedown="this.src=ImU.src" onclick="wMIN()">\n'+
		'</body>\n'+
		'</html>'

		cTIT=cTIT.replace(/\//g,"\\\/").replace(/\"/g,"\\\"").replace(/\n/g,"\\n")

		cRES=function(b,s){
			var tmp='\n'+
			'<html><head><META HTTP-EQUIV="imagetoolbar" CONTENT="no">\n'+
			'<script>\n'+
			'document.onmousemove=document.onselectstart=document.ondragstart=document.oncontextmenu=new Function("wMOV();return false");\n'+
			'b=-1\n'+
			'wMOV=function(){if(!top.mod){\n'+
			'if(b==0){top.sH=top.fH;top.sW=top.fW;b=-1}\n'+
			'if(b==2&&(1=='+b+'||4=='+b+'||5=='+b+')){tmp=event.screenY-oH;if(top.sH+tmp>100){top.fH=top.sH+tmp}}\n'+
			'if(b==2&&(2=='+b+'||4=='+b+')){tmp=event.screenX-oW;if(top.sW-tmp>100){top.fW=top.sW-tmp;top.px=event.screenX-ofx-1}}\n'+
			'if(b==2&&(3=='+b+'||5=='+b+')){tmp=event.screenX-oW;top.fW=top.sW+tmp}\n'+
			'if(b==2){setTimeout("top.rT(top.fW,top.fH);top.mT(top.px,top.py);",10)}\n'+
			'if(b==1){ofx=event.x;oH=event.screenY;oW=event.screenX;b=2}\n'+
			'}}</script></head>\n'+
			'<body bgcolor='+wBG+'>\n'+
			'<img style="cursor:'+s+'-resize" id=bMOV style="position:absolute;left:-50px;top:-50px" onmousemove="wMOV()" onmousedown="b=1;wMOV()" onmouseup="b=0;wMOV()" border=0 src="" width=3000 height=2000>\n'+
			'</body>\n'+
			'</html>'
			return tmp.replace(/\//g,"\\\/").replace(/\"/g,"\\\"").replace(/\n/g,"\\n")
		}

		var cRESd=cRES(1,'s'),cRESl=cRES(2,'w'),cRESr=cRES(3,'e'),cRESbl=cRES(4,'sw'),cRESbr=cRES(5,'se')

		var cFRM='<HTML><HEAD><TITLE>'+tW+'</TITLE>\n'+
		'<script>\n'+
		'ok=0;mod=0;sH=fH='+(H)+';sW=fW='+(W)+';px='+(X)+';py='+(Y)+'\n'+
		'bCOL=function(c1,c2){fT.document.bgColor=n0.document.bgColor=n1.document.bgColor=n2.document.bgColor=n3.document.bgColor=n4.document.bgColor=c1;bL.document.bgColor=bT.document.bgColor=bR.document.bgColor=bB.document.bgColor=c2}\n'+
		'mTIT=function(){if(frames.length>8){fT.document.write("'+cTIT+'");fT.document.close();if ('+res+'){n2.document.write("'+cRESd+'");n2.document.close();n1.document.write("'+cRESr+'");n1.document.close();n0.document.write("'+cRESl+'");n0.document.close();n3.document.write("'+cRESbl+'");n3.document.close();n4.document.write("'+cRESbr+'");n4.document.close()};top.bCOL("'+wBG+'","'+wB+'")}else{setTimeout("mTIT()",20)}}\n'+
		'mT=function(x,y){top.window.moveTo(x,y)}\n'+
		'rT=function(w,h){top.window.resizeTo(w,h)}\n'+
		'top.rT(fW,fH);top.mT(px,py)\n'+
		'mTIT()\n'+
		'</script></HEAD>\n'+
		'<frameset onselectstart="return false" onload="top.ok=1" onfocus="if (top.ok&&fT&&fT.wMAX) fT.wMAX(top.mod)" border=0 framespacing=0 frameborder=0 rows="'+tsz+',100%,'+brd+'">\n'+
		'	<frame name=fT src="about:blank" scrolling=no noresize>\n'+
		'	<frameset border=0 framespacing=0 frameborder=0 cols="'+brd+',1,100%,1,'+brd+'">\n'+
		'		<frame name=n0 src="about:blank" scrolling=no noresize>\n'+
		'		<frame name=bL src="about:blank" scrolling=no noresize>\n'+
		'			<frameset border=0 framespacing=0 frameborder=0 rows="1,100%,1">\n'+
		'				<frame name=bT src="about:blank" scrolling=no noresize>\n'+
		'				<frame name=frm_main src="'+u+'" '+fSO+'>\n'+
		'				<frame name=bB src="about:blank" scrolling=no noresize>\n'+
		'			</frameset>\n'+
		'		<frame name=bR src="about:blank" scrolling=no noresize>\n'+
		'		<frame name=n1 src="about:blank" scrolling=no noresize>\n'+
		'	</frameset>\n'+
		'	<frameset border=0 framespacing=0 frameborder=0 cols="'+brd+',100%,'+brd+'">\n'+
		'		<frame name=n3 src="about:blank" scrolling=no noresize>\n'+
		'		<frame name=n2 src="about:blank" scrolling=no noresize>\n'+
		'		<frame name=n4 src="about:blank" scrolling=no noresize>\n'+
		'	</frameset>\n'+
		'</frameset>\n'+
		'</HTML>'

		var CWIN=window.open("",n,"fullscreen=1"+s)
		CWIN.moveTo(5000,0)
		CWIN.ft=true
		CWIN.document.write(cFRM)
		CWIN.document.close()
	} else {
		var CWIN=window.open(u,n,wNS+s,true)
		CWIN.moveTo(X,Y)
	}
	CWIN.focus()
	CWIN.setURL=function(u) { if (this && !this.closed) { if (this.frames.frm_main) this.frames.frm_main.location.href=u; else this.location.href=u } }
	CWIN.closeIT=function() { if (this && !this.closed) this.close() }
	return CWIN
}

function openIT(u,W,H) {
	var n ='eibs'
	var X =null;
	var Y = null;
	var cU  =imgPath+'close_up.gif'
	var cO  =imgPath+'close_ovr.gif'
	var cL  =imgPath+'clock.gif'
	var mU  =imgPath+'min_up.gif'
	var mO  =imgPath+'min_ovr.gif'
	var xU  =imgPath+'max_up.gif'
	var xO  =imgPath+'max_ovr.gif'
	var rU  =imgPath+'res_up.gif'
	var rO  =imgPath+'res_ovr.gif'
	var tH  ='<font face=verdana size=1>eIBS - Datapro, Inc.</font>'
	var tW  ='eIBS - Datapro, Inc.'
	var wB  ='#000000'
	var wBs ='#FFFFFF'
	var wBG ='#ABBAD3'
	var wBGs='#ABBAD3'
	var wNS ='toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=0'
	var fSO ='scrolling=yes noresize'
	var brd =10;
	var ful =true;
	var min =false;
	var res =true;
	var tsz =20;
	chromeless(u,n,W,H,X,Y,cU,cO,cL,mU,mO,xU,xO,rU,rO,tH,tW,wB,wBs,wBG,wBGs,wNS,fSO,brd,ful,min,res,tsz)
}

function setCenterSize(w1,h1) {
		top.resizeTo(w1,h1);
		var w=screen.width;
		var h=screen.height;
		var posTop=(h-h1)/2;
		var posLeft=(w-w1)/2;
		top.moveTo(posLeft,posTop);

}

function GetPortfolioNum(name,type)
{
	page= prefix +language + "EWD0302_portfolio_help_container.jsp"
	fieldName=name;
	fieldId=type;
	CenterWindow(page,530,450,1);
}

function GetPortfolioNumDesc(port,cus,desc,cust)
{
	if(trim(cust).length < 1){
		alert("Please enter a valid Customer number.");
    } else {
		page = webapp + "/servlet/datapro.eibs.helps.JSEWD0302?FromRecord=0&shrCust=" + cust;
		fieldName=port;
		fieldDesc=cus;
		fieldAux1=desc;
		CenterWindow(page,620,400,2);
	}
}

function showRetAccountInq(code)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEDD0000I?SCREEN=1400&E01ACMACC=" + code;
	CenterWindow(page,560,500,2);
}

function showRetAccountHolders(code)
{
	page = webapp + "/servlet/datapro.eibs.invest.JSEIE0010I?SCREEN=7&HOLDERS=" + code;
	CenterWindow(page,560,500,2);
}

function showCustomerInq(code)
{
	page = webapp + "/servlet/datapro.eibs.client.JSESD0080?SCREEN=600&E01CUN=" + code;
	CenterWindow(page,560,500,2);
}

function GetCommCustodyTable(name,type,cust,table)
{
		page = webapp + "/servlet/datapro.eibs.helps.JSEWD0312?FromRecord=0&shrCust=" + cust + "&shrType=" + type + "&shrTable=" + table;
		fieldName=name;
		CenterWindow(page,530,450,1);

}

function showDetailsDebit(customer,debtyp,debsts)
{
	page = webapp + "/servlet/datapro.eibs.client.JSECIF200?SCREEN=500&E01CUSCUN=" + customer + "&DEBTYP=" + debtyp + "&DEBSTS=" + debsts;
	CenterWindow(page,560,500,2);
}

function showInqApprovalRT(convm) {
	page = webapp + "/servlet/datapro.eibs.misc.JSEDD1170?SCREEN=3&CONVM=" + convm;
	CenterWindow(page,600,500,2);

}


function GetAmortID(name,bank,branch)
{
	fieldName=name;
        page = webapp + "/servlet/datapro.eibs.helps.JSEWD0400?BNK=" + bank + "&BRN=" + branch;
	CenterWindow(page,700,400,2);

}

function GetAmortization(bnk,brn,ccy,gln,acc,acd)
{
   page= prefix +language + "EWD0405_amortization_help_container.jsp";
   bank = bnk;
   branch = brn;
   currency=ccy;
   glNumber=gln;
   account=acc;
   appCode = acd;
   CenterWindow(page,600,450,1);
}

function showInqApprovalSwiftFree(user,ref)
{
	page = webapp + "/servlet/datapro.eibs.forex.JSESWF010?SCREEN=9&USERID=" + user +"&REFNUM=" + ref;
	CenterWindow(page,560,500,2);
}

function GetSwiftIdDesc(name,desc)
{
	page= prefix +language + "EWD0130D_bankid_help_container.jsp"
	fieldName=name;
	fieldDesc=desc;
	PVia="S";
	CenterWindow(page,530,500,1);

}

function GetSwiftIdDesc(name,desc,city,ctry)
{
	page= prefix +language + "EWD0130D_bankid_help_container.jsp?args=4"
	fieldName=name;
	fieldDesc=desc;
	fieldAux1=city;
	fieldAux2=ctry;
	CenterWindow(page,530,500,1);

}

function GetSwiftAll(id,name,desc,city,ctry)
{
	page= prefix +language + "EWD0130T_bankid_help_container.jsp"
	fieldName=id;
	fieldDesc=name;
	fieldAux1=desc;
	fieldAux2=city;
	fieldAux3=ctry;
	PVia="S";
	CenterWindow(page,530,500,1);

}
function GetBanksAba(id,name,type)
{
	fieldName=id;
	fieldDesc=name;
	fieldAux1=type;
   	page = prefix +language + "EWD0360_help_container.jsp"
	CenterWindow(page,450,300,2);
}

function GetAbaAll(id,name,desc,city,ctry)
{
	page= prefix +language + "EWD0130T_bankid_help_container.jsp"
	fieldName=id;
	fieldDesc=name;
	fieldAux1=desc;
	fieldAux2=city;
	fieldAux3=ctry;
	PVia="F";
	CenterWindow(page,530,500,1);

}

function GetFedAll(id,name,desc,city,ctry)
{
	page= prefix +language + "EWD0130T_bankid_help_container.jsp"
	fieldName=id;
	fieldDesc=name;
	fieldAux1=desc;
	fieldAux2=city;
	fieldAux3=ctry;
	PVia="F";
	CenterWindow(page,530,500,1);

}

function GetBeneficiary(customer,acc,bad1,bad2,bad3,bkid,bka1,bka2,bka3,bka4,ibid,iba1,iba2,iba3,iba4)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0131?CUSTOMER=" + customer;
	fielName=customer;
	fieldAux1=acc;
	fieldAux2=bad1;
	fieldAux3=bad2; 
	fieldAux4=bad3;
	fieldAux5=bkid;
	fieldAux6=bka1;
	fieldAux7=bka2;
	fieldAux8=bka3;
	fieldAux9=bka4;
	fieldAux10=ibid;
	fieldAux11=iba1;
	fieldAux12=iba2;
	fieldAux13=iba3;
	fieldAux14=iba4;
	CenterWindow(page,600,500,2);
}

function DisabledRef(field1,field2,sel){

 if(sel == "A"){
	GetSwiftIdDesc(field1,field2);
 }
 if(sel == "D"){
  return;
 }
}

 function showInqSwift(fmt, ref, usr) {

	page =  webapp + "/servlet/datapro.eibs.forex.JSESWF0200?SCREEN=3&FORMAT=" + fmt + "&REFERENCE=" + ref + "&USERID=" + usr;
	CenterWindow(page,600,500,2);

}

function showInqSwiftI(fmt, ref, usr) {

	page =  webapp + "/servlet/datapro.eibs.forex.JSESWF0200I?SCREEN=3&FORMAT=" + fmt + "&REFERENCE=" + ref + "&USERID=" + usr;
	CenterWindow(page,600,700,2);

}


function showAverageAndInquiryPages(op, account) {

	page =  webapp + "/servlet/datapro.eibs.client.JSECIF170?SCREEN=4&opt=" + op + "&ACCNUM=" + account;
	CenterWindow(page,600,500,2);

}

function showProdAcc2(offCode,typ,prod)
{
	page = webapp + "/servlet/datapro.eibs.client.JSECIF170?SCREEN=6&offCode=" + offCode + "&type=" + typ + "&prodType=" + prod ;
	CenterWindow(page,600,500,2);

}

function GetCollDraft(name)
{
	page= prefix +language + "EWD0146_colldraft_help_container.jsp" ;
	fieldName=name;
	CenterWindow(page,530,450,1);
}


//*** ESTAS FUNCIONES SON REQUERIDAS PARA LA INTERACCION CON EL PINPAD
function openPINPAD(){


	var open = top.client.document.PINPAD.Iniciar("15") ;
	if( open != 0 ){
		var text = "" ;
		switch(open){
			case 100 :
				text = "Error. Puerto de comunicacion con el dispositivo." ;
				break ;

		}
		alert( text ) ;
		return false ;
	}else {
		return true ;
	}

}

function setPIN( cardnum ){

			var pinblock = top.client.document.PINPAD.AsignarPin( cardnum , document.form1.OPTION.value ) ;
			var text = "" ;
alert("PB:"+ pinblock + "-" + pinblock.length ) ;
			if( pinblock.length < 16 ){
				switch( pinblock ){
					case "150" :
						text = "Operación Cancelada" ;
						break ;

					case "900" :
						text = "Tarjeta no corresponde" ;
						break ;

					case "200" :
						text = "Los PIN Introducidos son distintos" ;
						break ;

					default :
						text = "Error en el dispositivo. Cod:" + pinblock ;
						break ;
				}
				alert( text ) ;

			}
			return pinblock ;

}


function setNewPIN(){

	//if( openPINPAD() && activeDevice() ){

			var pb = setPIN( document.forms[0].E01CCRNUM.value ) ;
			if( pb.length != 16 ){
				top.client.document.PINPAD.Salir();

			}else{
				if( document.form1.OPTION.value == "1" ){
					top.client.document.PINPAD.Salir();
				}
				//ASIGNAR EL PINBLOCK EN EL CAMPO EN EL QUE DEBE VIAJAR AL HOST
			}

//SE ASIGNA EL PINBLOCK CUALQUIERA QUE SEA PARA EFECTOS DE PRUEBAS
document.forms[0].E01CCRPIO.value = pb ;



	//}

	//top.client.document.PINPAD.Salir();

}

function activeDevice(){

	switch( top.client.document.PINPAD.ActivarLectura() ){
		case 0 :
			return true ;
			break ;
		default :
			alert("No se pudo activar el dispositivo.") ;
			return false ;
			break ;
	}

}

function actPinpad( field ){

	if( openPINPAD() && activeDevice() ){
		alert("Deslice la Tarjeta y luego presione Aceptar.") ;
		readTrack( field ) ;
	}else{
		top.client.document.PINPAD.Salir() ;
	}

}

function readPIN(){

	var pinblock = top.client.document.PINPAD.LeerPin( document.forms[0].E01CCRNUM.value ) ;
	var text = "" ;

alert("PB:"+ pinblock + "-" + pinblock.length ) ;

	if( pinblock.length < 16 ){
		switch( pinblock ){
			case "150" :
				text = "Operación Cancelada" ;
				break ;

			case "900" :
				text = "Tarjeta no corresponde" ;
				break ;

			case "200" :
				text = "Los PIN Introducidos son distintos" ;
				break ;

			default :
				text = "Error en el dispositivo. Cod:" + pinblock ;
				break ;
		}
		alert( text ) ;

	}else{
		document.forms[0].E01CCRPIO.value = pb ;
	}

	top.client.document.PINPAD.Salir() ;

}

function readTrack( field ){

	var form = top.main.document.forms[0];
	var resp = top.client.document.PINPAD.LeerTrack() ;
	var text = "" ;
	if( resp.length <= 3 ){
		switch( resp ){
			case "900" :
				text = "Tarjeta Inválida" ;
				break ;
			default :
				text = "Error leyendo la tarjeta. Cod:" + resp ;
				break ;
		}
		alert( text ) ;

	}else{
		//document.form1.field.value = resp.substring(2,21) ;
		form[field].value = resp.substring(2,21) ;
	}

	top.client.document.PINPAD.Salir() ;

}

// *** FIN FUNCIONES INTERACCION CON EL PINPAD

// Functions added from eIBS_Extended.jsp

function GetCodeDescCNOFC(name, desc, flag)
{
	page= prefix +language + "EWD0002_helpfile_CNOFC_Desc.jsp?codeflag=" + flag;
	fieldName=name;
	fieldDesc=desc;
	CenterWindow(page,320,330,2);
}

function GetCNOFCFilteredCodes(name, desc, filter1, flag)
{
  	fieldName=name;
	fieldDesc=desc;
	var filter=filter1;
    page= prefix +language + "EWD0002_helpfile_CNOFC_DescF.jsp?filter=EWDMID&EWDMID="+filter+"&codeflag=" + flag;
	//alert (page);
	CenterWindow(page,450,300,2);

}


function GetParroquiaCodes(name, desc, filter1, filter2,flag)
{

	fieldName=name;
	fieldDesc=desc;
	var filter="";
	//alert(filter1+ " , "+ filter2);
	if (filter1 != "")
	  filter=filter1;
	else
	  filter=filter2;
	page= prefix +language + "EWD0002_helpfile_CNOFC_DescF.jsp?filter=EWDMID&EWDMID="+filter+"&codeflag=" + flag;
	//alert (page);
	CenterWindow(page,450,300,2);
}


function showCollMaint(client) {

	var page= prefix +language + 'EDL0150_ln_coll_det.jsp?client='+client;
	CenterWindow(page,600,500,2);
}


function GetAccountAndProduct(name,product,bnk,app,sel)
{
   page= prefix +language + "EWD0005_client_help_container.jsp";
   fieldName=name;
   fieldProduct=product;
   AppCode = app;
   Bank = bnk;
   Selection=sel;
   CenterWindow(page,600,450,1);
}

// Propuesta de Credito

function GetProposalsProducts(name,typname,apcname,type)
{
	fieldName=name;
	fieldAux1=typname;
	fieldAux2=apcname;
   	page = webapp + "/servlet/datapro.eibs.helps.JSEDP0743?E01APCTYP=" + type;
	CenterWindow(page,450,300,2);
}

function GetFormsProposals(name,typname,type)
{
	fieldName=name;
	fieldAux1=typname;
	fieldAux2=type;
   	page = webapp + "/servlet/datapro.eibs.helps.JSEDP0752?E01APFPRO=9696";
	CenterWindow(page,450,300,2);
}

function GetWireOpening(name,amount)
{
	var page = webapp + "/servlet/datapro.eibs.helps.JSEPR0110?SCREEN=1";
	fieldName=name;
	fieldAux1=amount;
	CenterWindow(page,500,230,3);
}

function CrtCheck(name,loannbr)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=700&OPTION=Y" + "&VAL=" + name + "&LNSACC=" + loannbr;
	fieldName=name;
	CenterWindow(page,1000,900,3);
}

function MntCheck(name,loannbr)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=1400&OPTION=Y" + "&CHK=" + name + "&LNSACC=" + loannbr;
	fieldName=name;
	CenterWindow(page,1000,900,3);
}

function DelCheck(name,loannbr)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=1500&OPTION=Y" + "&CHK=" + name + "&LNSACC=" + loannbr;
	fieldName=name;
	CenterWindow(page,1000,900,3);
}

function InqCheck(name,loannbr)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=1600&OPTION=Y" + "&CHK=" + name + "&LNSACC=" + loannbr;
	fieldName=name;
	CenterWindow(page,1000,900,3);
}

function showInqColl(row) {
	page = webapp + "/servlet/datapro.eibs.client.JSERA0011?SCREEN=4&opt=4&ROW=" + row;
	CenterWindow(page,600,500,2);
}

function showInqPoliza(row) {
	page = webapp + "/servlet/datapro.eibs.client.JSERA0011?SCREEN=6&opt=4&ROW=" + row;
	CenterWindow(page,600,500,2);
}

function getClausula(type,screen,servlet)
{
	var page = webapp + "/servlet/datapro.eibs.products.JSEWP0010?TYPE=" + type + "&SCREEN=" + screen + "&SERVLET=" + servlet;
	CenterWindow(page,500,280,3);
}

function GetCountryCodeOfac(name){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0351";
	fieldName=name;
	CenterWindow(page,500,430,3);
}

function GetDenialCodeOfac(name){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0352";
	fieldName=name;
	CenterWindow(page,500,430,3);
}

function GetInsuranceFormula(name){

    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0370";
	fieldName=name;
	CenterWindow(page,400,300,3);
}

// To test a rate value consisting of maximum 3 integers and maximum 6 decimals
function enterRate()
{
 var elem=event.srcElement;
 var kcode=event.keyCode;
 var val;
 var value = elem.value ;
 var oldVal= elem.value ;
 value = value + String.fromCharCode(kcode);

 if( ! value.match( (/^\d{1,3}(\.\d{0,6})?$/ ) ) ){
 	elem.value = oldVal ;
 	event.returnValue = false ;
 }

}

function CrtCheck(name,loannbr)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=700&OPTION=Y" + "&VAL=" + name + "&LNSACC=" + loannbr;
	fieldName=name;
	CenterWindow(page,1000,900,3);
}


function GetAchOperator(code,type,name,type1){

	page = webapp + "/servlet/datapro.eibs.ach.JSEWD0360?Type=" + type1;
	fieldName=code;
	fieldAux1=type;
	fieldDesc=name;
	CenterWindow(page,600,450,2);
}

function GetExternalAccountACH(benename, beneaddress, benecsz, beneemail, routing, externalacc, type,customer)
{
	page= prefix +language + "EWD0362_ach_helpfile.jsp?customer="+ customer;
	fieldAux1=benename;
	fieldAux2=beneaddress;
	fieldAux3=benecsz;
	fieldAux4=beneemail;
	fieldAux5=routing;
	fieldAux6=externalacc;
	fieldAux7=type;

	CenterWindow(page,700,400,2);
}

function GetAchClass(code,name){

	page = webapp + "/servlet/datapro.eibs.ach.JSEWD0361";
	fieldName=code;
	fieldDesc=name;
	CenterWindow(page,600,450,2);
}

function GetAchTransaction(code,name){

	page = webapp + "/servlet/datapro.eibs.ach.JSEWD0363";
	fieldName=code;
	fieldDesc=name;
	CenterWindow(page,600,450,2);
}

function InqCheck(name,loannbr)
{
	page = webapp + "/servlet/datapro.eibs.products.JSEOF0115?SCREEN=1600&OPTION=Y" + "&CHK=" + name + "&LNSACC=" + loannbr;
	fieldName=name;
	CenterWindow(page,800,600,3);
}

function GetLimit(name,bank,code)
{
	fieldName=name;
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0380?bnk=" + bank + "&tbl=" + code;
	CenterWindow(page,600,400,2);
}

function GetReferenceCode(code)
{
	page = webapp + "/servlet/datapro.eibs.helps.JSEWD0710";
	fieldName=code;
	CenterWindow(page,500,450,3);
}


function GetFloatingRate(name,prod)
{
 page = webapp + "/servlet/datapro.eibs.helps.JSEWD0103?PROD=" + prod;
 fieldName=name;
 CenterWindow(page,600,140,3);
}

function GetProfileTable(name,desc){
    page = webapp + "/servlet/datapro.eibs.helps.JSEWD0355";
 fieldName=name;
 fieldAux1=desc;
 CenterWindow(page,500,230,3);
}