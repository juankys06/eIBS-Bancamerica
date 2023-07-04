<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Producto
</TITLE>

<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id="msgRT" class="datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id="msgRTC" class="datapro.eibs.beans.EDP072201Message"  scope="session" />
<jsp:useBean id="msgList" class="datapro.eibs.beans.EDP072101Message"  scope="session" />
<jsp:useBean id="EDP072101Help" class="datapro.eibs.beans.JBObjList" scope="session" />

<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNP0" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN19" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN30" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN38" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNPT" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNPR" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCN85" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNPU" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optCNPS" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "optList4"  class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
 
 


<%
if (!error.getERRNUM().equals("0")) {
      error.setERRNUM("0");
%>
	<SCRIPT Language="Javascript">
		showErrors();
		</SCRIPT>
<%}%>


<SCRIPT language="JavaScript">

builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
}

function initdp()
{
// texto comentarios 
if (document.forms[0].E02DPAC00 != null) {
	document.forms[0].E02DPAC00.value = "<%=msgRTC.getE02DPAC00().trim()%>";
}
if (document.forms[0].E02DPAC01 != null) {
	document.forms[0].E02DPAC01.value = "<%=msgRTC.getE02DPAC01().trim()%>";
}
if (document.forms[0].E02DPAC02 != null) {
	document.forms[0].E02DPAC02.value = "<%=msgRTC.getE02DPAC02().trim()%>";
}
if (document.forms[0].E02DPAC03 != null) {
	document.forms[0].E02DPAC03.value = "<%=msgRTC.getE02DPAC03().trim()%>";
}
if (document.forms[0].E02DPAC04 != null) {
	document.forms[0].E02DPAC04.value = "<%=msgRTC.getE02DPAC04().trim()%>";
}
if (document.forms[0].E02DPAC05 != null) {
	document.forms[0].E02DPAC05.value = "<%=msgRTC.getE02DPAC05().trim()%>";
}
if (document.forms[0].E02DPAC06 != null) {
	document.forms[0].E02DPAC06.value = "<%=msgRTC.getE02DPAC06().trim()%>";
}
if (document.forms[0].E02DPAC07 != null) {
	document.forms[0].E02DPAC07.value = "<%=msgRTC.getE02DPAC07().trim()%>";
}
if (document.forms[0].E02DPAC08 != null) {
	document.forms[0].E02DPAC08.value = "<%=msgRTC.getE02DPAC08().trim()%>";
}
if (document.forms[0].E02DPAC09 != null) {
	document.forms[0].E02DPAC09.value = "<%=msgRTC.getE02DPAC09().trim()%>";
}
if (document.forms[0].E02DPAC10 != null) {
	document.forms[0].E02DPAC10.value = "<%=msgRTC.getE02DPAC10().trim()%>";
}
if (document.forms[0].E02DPAC11 != null) {
	document.forms[0].E02DPAC11.value = "<%=msgRTC.getE02DPAC11().trim()%>";
}
if (document.forms[0].E02DPAC12 != null) {
	document.forms[0].E02DPAC12.value = "<%=msgRTC.getE02DPAC12().trim()%>";
}
if (document.forms[0].E02DPAC13 != null) {
	document.forms[0].E02DPAC13.value = "<%=msgRTC.getE02DPAC13().trim()%>";
}
if (document.forms[0].E02DPAC14 != null) {
	document.forms[0].E02DPAC14.value = "<%=msgRTC.getE02DPAC14().trim()%>";
}
if (document.forms[0].E02DPAC15 != null) {
	document.forms[0].E02DPAC15.value = "<%=msgRTC.getE02DPAC15().trim()%>";
}
if (document.forms[0].E02DPAC16 != null) {
	document.forms[0].E02DPAC16.value = "<%=msgRTC.getE02DPAC16().trim()%>";
}
if (document.forms[0].E02DPAC98 != null) {
	document.forms[0].E02DPAC98.value = "<%=msgRTC.getE02DPAC98().trim()%>";
}
if (document.forms[0].E02DPAC99 != null) {
	document.forms[0].E02DPAC99.value = "<%=msgRTC.getE02DPAC99().trim()%>";
}
var boxLength = document.forms[0].E01PLTPRO.length;
var thisvalue;
var i;
i = 0;
document.forms[0].E01PLTPRO.selectedIndex=-1;

if (boxLength != 0) {

for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01PLTPRO.options[i].value;
selectedText = document.forms[0].E01PLTPRO.options[i].text;
document.forms[0].E01PLTTYP.value = selectedText.substring(4,9)
if (thisvalue == document.forms[0].PROD.value) {
	document.forms[0].E01PLTPRO.selectedIndex=i;
	var c2 = selectedText.charAt(12);
	if (c2 == "R") { 
		hideAgricola(false);
		document.forms[0].AGRIC.value = '1'
	} else {
		hideAgricola(true);
		document.forms[0].AGRIC.value = '0'
	}
	if (thisvalue == '9999') {
		document.forms[0].CART.value = '1'
	} else {
		document.forms[0].CART.value = '0'
	}

	break;
   }
}

	if ("<%=msgRT.getE01PLPPTY().trim()%>" =="6") {
		hideOthers(false);
	} else {
		hideOthers(true);
	}

}

if (document.forms[0].E01PLTCCY != null) {

var boxLength = document.forms[0].E01PLTCCY.length;
i = 0;
document.forms[0].E01PLTCCY.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01PLTCCY.options[i].value;
if (thisvalue == document.forms[0].CCY.value) {
	document.forms[0].E01PLTCCY.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01PLTDST != null) {

var boxLength = document.forms[0].E01PLTDST.length;
i = 0;
document.forms[0].E01PLTDST.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01PLTDST.options[i].value;
if (thisvalue == document.forms[0].DST.value) {
	document.forms[0].E01PLTDST.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01PLTORI != null) {

var boxLength = document.forms[0].E01PLTORI.length;
i = 0;
document.forms[0].E01PLTORI.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01PLTORI.options[i].value;
if (thisvalue == document.forms[0].ORI.value) {
	document.forms[0].E01PLTORI.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01DPPDR0 != null) {
var boxLength = document.forms[0].E01DPPDR0.length;
i = 0;
document.forms[0].E01DPPDR0.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPDR0.options[i].value;
if (thisvalue == document.forms[0].UN0.value) {
	document.forms[0].E01DPPDR0.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPDR1 != null) {
document.forms[0].E01DPPDR1.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPDR1.options[i].value;
if (thisvalue == document.forms[0].UN1.value) {
	document.forms[0].E01DPPDR1.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPDR2 != null) {
document.forms[0].E01DPPDR2.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPDR2.options[i].value;
if (thisvalue == document.forms[0].UN2.value) {
	document.forms[0].E01DPPDR2.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPDR3 != null) {
document.forms[0].E01DPPDR3.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPDR3.options[i].value;
if (thisvalue == document.forms[0].UN3.value) {
	document.forms[0].E01DPPDR3.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPDR4 != null) {
document.forms[0].E01DPPDR4.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPDR4.options[i].value;
if (thisvalue == document.forms[0].UN4.value) {
	document.forms[0].E01DPPDR4.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPDR5 != null) {
document.forms[0].E01DPPDR5.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPDR5.options[i].value;
if (thisvalue == document.forms[0].UN5.value) {
	document.forms[0].E01DPPDR5.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01DPPRB0 != null) {
var boxLength = document.forms[0].E01DPPRB0.length;
i = 0;
document.forms[0].E01DPPRB0.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPRB0.options[i].value;
if (thisvalue == document.forms[0].RB0.value) {
	document.forms[0].E01DPPRB0.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPRB1 != null) {
document.forms[0].E01DPPRB1.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPRB1.options[i].value;
if (thisvalue == document.forms[0].RB1.value) {
	document.forms[0].E01DPPRB1.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPRB2 != null) {
document.forms[0].E01DPPRB2.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPRB2.options[i].value;
if (thisvalue == document.forms[0].RB2.value) {
	document.forms[0].E01DPPRB2.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPRB3 != null) {
document.forms[0].E01DPPRB3.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPRB3.options[i].value;
if (thisvalue == document.forms[0].RB3.value) {
	document.forms[0].E01DPPRB3.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPRB4 != null) {
document.forms[0].E01DPPRB4.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPRB4.options[i].value;
if (thisvalue == document.forms[0].RB4.value) {
	document.forms[0].E01DPPRB4.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPRB5 != null) {
document.forms[0].E01DPPRB5.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPRB5.options[i].value;
if (thisvalue == document.forms[0].RB5.value) {
	document.forms[0].E01DPPRB5.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01DPPSB0 != null) {
var boxLength = document.forms[0].E01DPPSB0.length;
i = 0;
document.forms[0].E01DPPSB0.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPSB0.options[i].value;
if (thisvalue == document.forms[0].SB0.value) {
	document.forms[0].E01DPPSB0.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPSB1 != null) {
document.forms[0].E01DPPSB1.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPSB1.options[i].value;
if (thisvalue == document.forms[0].SB1.value) {
	document.forms[0].E01DPPSB1.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPSB2 != null) {
document.forms[0].E01DPPSB2.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPSB2.options[i].value;
if (thisvalue == document.forms[0].SB2.value) {
	document.forms[0].E01DPPSB2.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPSB3 != null) {
document.forms[0].E01DPPSB3.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPSB3.options[i].value;
if (thisvalue == document.forms[0].SB3.value) {
	document.forms[0].E01DPPSB3.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPSB4 != null) {
document.forms[0].E01DPPSB4.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPSB4.options[i].value;
if (thisvalue == document.forms[0].SB4.value) {
	document.forms[0].E01DPPSB4.selectedIndex=i;
	break;
   }
}
}
}

i = 0;
if (document.forms[0].E01DPPSB5 != null) {
document.forms[0].E01DPPSB5.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPPSB5.options[i].value;
if (thisvalue == document.forms[0].SB5.value) {
	document.forms[0].E01DPPSB5.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01DPZTRE != null) {
var boxLength = document.forms[0].E01DPZTRE.length;
i = 0;
document.forms[0].E01DPZTRE.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01DPZTRE.options[i].value;
if (thisvalue == document.forms[0].TRE.value) {
	document.forms[0].E01DPZTRE.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01PLTOTH != null) {
var boxLength = document.forms[0].E01PLTOTH.length;
i = 0;
document.forms[0].E01PLTOTH.selectedIndex=0;
if (boxLength != 0) {
for (i = 0; i < boxLength; i++) {
thisvalue = document.forms[0].E01PLTOTH.options[i].value;
if (thisvalue == document.forms[0].OTR.value) {
	document.forms[0].E01PLTOTH.selectedIndex=i;
	break;
   }
}
}
}

if (document.forms[0].E01PLTCCA != null) {
var selectedItem = document.forms[0].E01PLTCCA.selectedIndex;
var selectedV = document.forms[0].E01PLTCCA.options[selectedItem].value;
if (selectedV == '8') {
	document.forms[0].E01PLTCIN.selectedIndex=-1;
	document.forms[0].E01PLTCBI.selectedIndex=-1;
	document.forms[0].E01PLTRTE.value=0;
}
}

// VENTANA POR ESTADO = 70

if ("<%=msgRT.getE01PLPEST().trim()%>" >= "70") {
		hideCondiciones(false);
	} else {
		hideCondiciones(true);
	}

}

function goConfirm(opt,TASK, OPECOD,PARAM) {
	document.forms[0].OPECOD.value = OPECOD;
	document.forms[0].PARAM.value = PARAM;
   	document.forms[0].TASK.value = TASK;

	document.forms[0].E02DPAPRO.value = document.forms[0].E01PLTPRO.value ;
	document.forms[0].TYP.value = document.forms[0].E01PLTTYP.value ;

	var op = opt;  	  
	document.forms[0].opt.value = op;

//VALIDATIONS
	var error = 0;
	var smsg1 = "";
	var smsg2 = "";
	var smsg3 = "";
	var smsg4 = "";
	var smsg5 = "";
	var smsg6 = "";
	var smsg7 = "";
	var smsg8 = "";
	var smsg9 = "";
	var smsg10 = "";
	var smsg11 = "";
	var smsg12 = "";
	var smsg13 = "";
	var emsg1 = "";
	var emsg2 = "";
	var emsg3 = "";
	var emsg4 = "";
	var emsg5 = "";
	var emsg6 = "";
	var emsg7 = "";
	var emsg8 = "";
	var emsg9 = "";
	var emsg10 = "";
	var emsg11 = "";
	var emsg12 = "";
	var emsg13 = "";
	
	if (document.forms[0].E01PLTUPL != null) {
	if (document.forms[0].E01PLTUPL.value == "8") { 
		if (document.forms[0].E02DPAC02.value == "") { 
		smsg1 = 'Especifique descripcion plazo cuando se selecciona OTROS \n\n';
		emsg1 = 'Especify description time limit when is selected OTHERS \n\n';
		error = 1;
  	  document.forms[0].E02DPAC02.focus();

		}
	}
	}

	if (document.forms[0].E01PLTCCA != null) {
	if (document.forms[0].E01PLTCCA.value == "8") { 
		if (document.forms[0].E02DPAC04.value == "") { 
		smsg2 = 'Especifique descripcion forma de pago capital cuando se selecciona OTROS \n\n';
		emsg2 = 'Specify capital payment form description when is selected OTHER \n\n';
		error = 1;
  	  document.forms[0].E02DPAC04.focus();

		}
	}
	}

	if (document.forms[0].E01PLTCIN != null) {
	if (document.forms[0].E01PLTCIN.value == "8") { 
		if (document.forms[0].E02DPAC06.value == "") { 
		smsg3 = 'Especifique descripcion forma de pago intereses cuando se selecciona OTROS \n\n';
		emsg3 = 'Specify interests payment form description when is selected OTHER \n\n';
		error = 1;
  	  document.forms[0].E02DPAC06.focus();

		}
	}
	}

	if (document.forms[0].E02DPAC00 != null) {
	if ("<%=msgRTC.getE02DPJC00().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC00.value == "") { 
		smsg4 = 'Especifique Comentarios para Destino Fondos \n\n';
		emsg4 = 'Specify Comments for Destiny Funds \n\n';
		error = 1;
  	  document.forms[0].E02DPAC00.focus();

		}
	}
	}

	if (document.forms[0].E02DPAC01 != null) {
	if ("<%=msgRTC.getE02DPJC01().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC01.value == "") { 
		smsg5 = 'Especifique Comentarios para Plazo/Tiempo \n\n';
		emsg5 = 'Specify Comments for time Limit \n\n';
		error = 1;
  	  document.forms[0].E02DPAC01.focus();
		}
	}
	}

	if (document.forms[0].E01PLTCCA != null) {
	if ("<%=msgRTC.getE02DPJC02().trim()%>" == "2") { 
		if (!document.forms[0].E01PLTCCA.value == "8") { 
	
		if (document.forms[0].E02DPAC02.value == "") { 
		smsg6 = 'Especifique Comentarios para Otro Plazo/Tiempo \n\n';
		emsg6 = 'Specify Comments for other time Limit \n\n';
		error = 1;
  	  document.forms[0].E02DPAC02.focus();

		}
	}
	}
	}

	if (document.forms[0].E02DPAC03 != null) {
	if ("<%=msgRTC.getE02DPJC03().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC03.value == "") { 
		smsg7 = 'Especifique Comentarios para Forma de Pago Capital \n\n';
		emsg7 = 'Specify Comments for Form of Capital Payment \n\n';
		error = 1;
  	  document.forms[0].E02DPAC03.focus();

		}
	}
	}

	if (document.forms[0].E02DPAC04 != null) {
	if ("<%=msgRTC.getE02DPJC04().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC04.value == "") { 
		smsg8 = 'Especifique Comentarios para Otra Forma de Pago Capital \n\n';
		emsg8 = 'Specify Comments for other Form of Capital Payment \n\n';
		error = 1;
  	  document.forms[0].E02DPAC04.focus();

		}
	}
	}
	
	if (document.forms[0].E02DPAC05 != null) {
	if ("<%=msgRTC.getE02DPJC05().trim()%>" == "2") { 
		if (!document.forms[0].E01PLTCIN.value == "8") { 
		if (document.forms[0].E02DPAC05.value == "") { 
		smsg9 = 'Especifique Comentarios para Forma de Pago Intereses \n\n';
		emsg9 = 'Specify Comments for Form of Interest Payment \n\n';
		error = 1;
  	  document.forms[0].E02DPAC05.focus();
	}
	}
	}
	}

	if (document.forms[0].E02DPAC06 != null) {
	if ("<%=msgRTC.getE02DPJC06().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC06.value == "") { 
		smsg10 = 'Especifique Comentarios para Otro Forma de Pago Intereses \n\n';
		emsg10 = 'Specify Comments for other Form of Interest Payment \n\n';
		error = 1;
  	  document.forms[0].E02DPAC06.focus();

		}
	}
	}

	if (document.forms[0].E02DPAC07 != null) {
	if ("<%=msgRTC.getE02DPJC07().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC07.value == "") { 
		smsg11 = 'Especifique Comentarios para Comision o Girado \n\n';
		emsg11 = 'Specify Comments for commission \n\n';
		error = 1;
  	  document.forms[0].E02DPAC07.focus();

		}
	}
	}

	if (document.forms[0].E02DPAC08 != null) {
	if ("<%=msgRTC.getE02DPJC08().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC08.value == "") { 
		smsg12 = 'Especifique Comentarios para Fuente de Repago \n\n';
		emsg12 = 'Specify Comments for repayment source \n\n';
		error = 1;
  	  document.forms[0].E02DPAC08.focus();

		}
	}
	}

	if (document.forms[0].E02DPAC09 != null) {
	if ("<%=msgRTC.getE02DPJC09().trim()%>" == "2") { 
		if (document.forms[0].E02DPAC09.value == "") { 
		smsg13 = 'Especifique Comentarios para Garantias/Avales \n\n';
		emsg13 = 'Specify Comments for collaterals/avals \n\n';
		error = 1;
  	  document.forms[0].E02DPAC09.focus();
		}
	}
	}


	if (error  == 0){	

	
    document.forms[0].SCREEN.value="460";

	document.forms[0].submit();

	} else {
		if (document.forms[0].LAN.value == 'S') {
			alert(smsg1+ smsg2 + smsg3 + smsg4 + smsg5 + smsg6 + smsg7 + smsg8 + smsg9 + smsg10 + smsg11 + smsg12+ smsg13);
		} else{
			alert(emsg1+ emsg2 + emsg3 + emsg4 + emsg5 + emsg6 + emsg7 + emsg8 + emsg9 + emsg10 + emsg11 + emsg12+ emsg13);
		}
	}
}

function hideAgricola(value){
 if (value) {
   Agricola.style.display="none";}
 else{
   Agricola.style.display=""; }
}

function hideOthers(value){
 if (value) {
   Others.style.display="none";}
 else{
   Others.style.display=""; 
   }
}

function hideRedaccion(value){
 if (value) {
//   Redaccion.style.display="none";
} else{
//   Redaccion.style.display=""; 
   }
}

function hideCondiciones(value){
 if (value) {
   Condiciones.style.display="none";}
 else{
   Condiciones.style.display=""; 
   }
}

function evalAgric(){
var selectedItem = document.forms[0].E01PLTPRO.selectedIndex;
var selectedText = document.forms[0].E01PLTPRO.options[selectedItem].text;
var selectedValue = document.forms[0].E01PLTPRO.options[selectedItem].value;

document.forms[0].E01PLTTYP.value = selectedText.substring(4,9)


document.forms[0].SCREEN.value = 740;
document.forms[0].submit();



var c2 = selectedText.charAt(12);
if (c2 == "R") { 
	hideAgricola(false);
} else {
	hideAgricola(true);
}

if (selectedValue == '9999') {
	hideRedaccion(false);
} else {
	hideRedaccion(true);
}

}


function goCancel(opt) {
	var op = opt;  	  
	document.forms[0].opt.value = op;
document.forms[0].SCREEN.value="470"; 
document.forms[0].submit(); 
}

function selK() {
var selectedItem = document.forms[0].E01PLTCCA.selectedIndex;
var selectedV = document.forms[0].E01PLTCCA.options[selectedItem].value;
if (selectedV == '8') {
	document.forms[0].E01PLTCIN.selectedIndex=-1;
	document.forms[0].E01PLTCBI.selectedIndex=-1;
	document.forms[0].E01PLTRTE.value=0;
	document.forms[0].E01PLTPLZ.value=0;
	document.forms[0].E01PLTUPL.selectedIndex=-1;
}
var pagare = document.forms[0].E01XXXREN.value;

if (pagare == "1") {
	var op_forma_pago = document.forms[0].E01PLTCCA.options[selectedItem].value;
	var periodo = document.forms[0].E01PLTPLZ.value;
	alert(op_forma_pago);
	document.forms[0].E02DPAC03.value="";			
	switch (op_forma_pago) { 
		case  '1': 
				document.forms[0].E02DPAC03.value="Pagare a un dia prorragrable diario"; 
				break;
		case  '2': 
				document.forms[0].E02DPAC03.value="Pagare a 30 dias dias prorragrable mensual"; 
				break;
	   case  '3': 
				document.forms[0].E02DPAC03.value="Pagare a 60 dias dias prorragrable periodos bimensuales";
				break;
		case  '4': 
				document.forms[0].E02DPAC03.value="Pagare a 90 dias dias prorragrable periodos trimestrales"; 
				break;		
		case  '5': 
				document.forms[0].E02DPAC03.value="Pagare a 180 dias dias prorragrable periodos semestrales"; 
				break;
	   case  '6':
	   			alert(periodo)	 
	   			if (periodo > 1) {
	 		    	document.forms[0].E02DPAC03.value="Pagare a 360 dias dias prorragrable periodos anuales"; 
	 		    } else {
	 		       	document.forms[0].E02DPAC03.value=""; 
	 		    }	
		        break;
	   case  '8':
	   			document.forms[0].E02DPAC03.value="Pagare prorrogable por los periodos sucesivos del capital hasta el vencimiento del plazo del credito indicado en la forma de pago";
	   default :document.forms[0].E02DPAC03.value="";			
	};
 }; 	
}
</SCRIPT>

</HEAD>

<body onload=javascript:initdp()>

<h3 align="center">Detalle del Producto<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_product_maint.jsp,EDP0720"></h3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="460">
  <input type=HIDDEN name="PROD" value="<%= msgRTC.getE02DPAPRO().trim()%>">
  <input type=HIDDEN name="CCY" value="<%= msgList.getE01PLTCCY().trim()%>">
  <input type=HIDDEN name="DST" value="<%= msgList.getE01PLTDST().trim()%>">
  <input type=HIDDEN name="ORI" value="<%= msgList.getE01PLTORI().trim()%>">
  <input type=HIDDEN name="BNK" value="<%= userPO.getBank()%>"> 
  <input type=HIDDEN name="BRN" value="<%= userPO.getBranch()%>"> 
  <input type=HIDDEN name="RUT" value="<%= userPO.getHeader15().trim()%>"> 
  <input type=HIDDEN name="CUS" value="<%= userPO.getCusNum()%>"> 
  <input type=HIDDEN name="OTR" value="<%= msgList.getE01PLTOTH().trim()%>">


  <input type=HIDDEN name="E01XXXREN" value="<%= msgRTC.getE02XXXREN().trim()%>">
  <input type=HIDDEN name="E01PLTFL1" value="<%= msgList.getE01PLTFL1().trim()%>">
  <input type=HIDDEN name="OPECOD" value="<%= msgRT.getE01DPWOPC().trim()%>">
  <input type=HIDDEN name="PARAM" value="<%= msgRT.getE01DPWPAR().trim()%>">
  <input type=HIDDEN name="TASK" value="<%= userPO.getHeader23().trim()%>">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= userPO.getHeader16().trim()%>">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK3" VALUE="<%= msgRT.getH01FLGWK3().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02DPAC98" VALUE="<%= msgRTC.getE02DPAC98().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02DPAC99" VALUE="<%= msgRTC.getE02DPAC99().trim()%>">
  <input type=HIDDEN name="UN0" value="<%= msgList.getE01DPPDR0().trim()%>">
  <input type=HIDDEN name="UN1" value="<%= msgList.getE01DPPDR1().trim()%>">
  <input type=HIDDEN name="UN2" value="<%= msgList.getE01DPPDR2().trim()%>">
  <input type=HIDDEN name="UN3" value="<%= msgList.getE01DPPDR3().trim()%>">
  <input type=HIDDEN name="UN4" value="<%= msgList.getE01DPPDR4().trim()%>">
  <input type=HIDDEN name="UN5" value="<%= msgList.getE01DPPDR5().trim()%>">
  <input type=HIDDEN name="RB0" value="<%= msgList.getE01DPPRB0().trim()%>">
  <input type=HIDDEN name="RB1" value="<%= msgList.getE01DPPRB1().trim()%>">
  <input type=HIDDEN name="RB2" value="<%= msgList.getE01DPPRB2().trim()%>">
  <input type=HIDDEN name="RB3" value="<%= msgList.getE01DPPRB3().trim()%>">
  <input type=HIDDEN name="RB4" value="<%= msgList.getE01DPPRB4().trim()%>">
  <input type=HIDDEN name="RB5" value="<%= msgList.getE01DPPRB5().trim()%>">
  <input type=HIDDEN name="SB0" value="<%= msgList.getE01DPPSB0().trim()%>">
  <input type=HIDDEN name="SB1" value="<%= msgList.getE01DPPSB1().trim()%>">
  <input type=HIDDEN name="SB2" value="<%= msgList.getE01DPPSB2().trim()%>">
  <input type=HIDDEN name="SB3" value="<%= msgList.getE01DPPSB3().trim()%>">
  <input type=HIDDEN name="SB4" value="<%= msgList.getE01DPPSB4().trim()%>">
  <input type=HIDDEN name="SB5" value="<%= msgList.getE01DPPSB5().trim()%>">
  <input type=HIDDEN name="TRE" value="<%= msgList.getE01DPZTRE().trim()%>">
  <input type=HIDDEN name="ACD" value="<%= msgList.getE01PLTACD().trim()%>">
  <input type=HIDDEN name="E02DPAPRO" value="">
  <input type=HIDDEN name="AGRIC" value="">
  <input type=HIDDEN name="CART" value="">
  <input type=HIDDEN name="TYP" value="">
  <INPUT TYPE=HIDDEN NAME="LAN" value="<%= msgList.getE01CNTLAN().trim()%>">

	<% if(msgRTC.getE02DPJC00().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC00" value="">  
	<% } %>
	
	<% if(msgRTC.getE02DPJC01().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC01" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC02().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC02" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC03().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC03" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC04().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC04" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC05().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC05" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC06().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC06" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC07().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC07" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC08().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC08" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC09().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC09" value="">
	<% } %>

	<% if(msgRTC.getE02DPJC11().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC11" value="">
	<% } %>

	<% if(msgRTC.getE02DPJC12().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC12" value="">
	<% } %>

	<% if(msgRTC.getE02DPJC13().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC13" value="">
	<% } %>
	
	<% if(msgRTC.getE02DPJC14().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC14" value="">
	<% } %>
	<% if(msgRTC.getE02DPJC15().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC15" value="">
	<% } %>
	<% if(msgRTC.getE02DPJC16().equals("3")){%>
	  <input type=HIDDEN name="E02DPAC16" value="">
	<% } %>
	
<hr size="4">

   <TABLE id="headTable" width="100%" cellpading=0 cellspacing=0>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Cliente :</b>
      </td>
      <td nowrap > 
         <input type=TEXT name="E01CUSCUN" size=10 maxlength=9  value="<%= msgRT.getE01CUSCUN().trim()%>" readonly>
      </td>
      <td align="right"  >
         <b>Nombre :</b>
      </td>
      <td nowrap colspan=3> 
   		<input type="text" name="E01CUSNA1" size="27" maxlength="4" value="<%= msgRT.getE01CUSNA1().trim()%>" readonly>
      </td>
    </tr> 
    <tr id="trdark"> 
      <td align="right"  >
         <b>Nro. Propuesta :</b>
      </td>
      <td nowrap > 
		<INPUT type="text" name="E01PLPNPR" size="12" maxlength="12" value="<%= msgRT.getE01PLPNPR().trim()%>" readonly>
      </td>
      <td align="right"  >
         <b>Ref. IBS :</b>
      </td>
      <td nowrap colspan=3> 
   		<input type="text" name="E01PLTREF" size="17" maxlength="4" value="<%= msgRT.getE01PLTREF().trim()%>" readonly>
      </td>
    </tr>
    <tr id="trdark"> 
      <td align="right"  >
         <b>Saldo Actual :</b>
      </td>
      <td nowrap > 
		<INPUT type="text" name="E01PLTBAL" size="12" maxlength="12" value="<%= msgList.getE01PLTBAL().trim()%>" readonly>
      </td>
      <% if(msgRTC.getE02DPJF02().equals("1")){%> 
       <td align="right"  >
         	<b>Monto a Cancelar :</b>
       </td>
       <td nowrap colspan=3> 
        <% if((!msgRT.getE01PLPPTY().equals("2")) || (!msgRT.getE01PLPPTY().equals("6"))){%>
   		  <INPUT type="text" name="E01PLTPAY" size="20" maxlength="20" value="<%= msgList.getE01PLTPAY().trim()%>"
   		  <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
		  <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		  >
		<% }; %>
      </td>
     <% }; %> 
    </tr>
  </table>
   
  <table class="tableinfo">
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			
			<TR id="trclear">
			   <TD width="20%">
                    <DIV align="right">Producto:</DIV>
				</TD>
				<TD width="70%">
				  <SELECT name="E01PLTPRO" onchange="evalAgric();" <% if(!msgRTC.getE02DPAPRO().equals("")) {out.print(" DISABLED ");}%>>
					<%
                     optList4.initRow();
                     while (optList4.getNextRow()) {
                      if (optList4.getFlag().equals("")) {
                    		out.println(optList4.getRecord());
                      }
                     }
    			    %>
				  </SELECT>
				  Tipo: 
				  <INPUT type="text" name="E01PLTTYP" size="5" maxlength="4" value="<%= msgList.getE01PLTTYP().trim()%>" readonly>   
				  <INPUT type="text" name="E01PLPPTY" size="5" maxlength="4" value="<%= msgRT.getE01PLPPTY().trim()%>" readonly></TD>
			</TR>
		
			<tr id="clear">
			   <td width="10%">
				 <% if(!msgRTC.getE02DPJF00().equals("0")){%>
				  <div align="right">Monto Solicitado:</div>
				 </td>
				<td width="15%">
				<INPUT type="text" name="E01PLTAMN" size="20" maxlength="20" value="<%= msgList.getE01PLTAMN().trim()%>"
				<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
				>
				<% }; %>

				<% if(msgRTC.getE02DPJF01().equals("1")){%>
				Moneda:
				<SELECT name="E01PLTCCY"  
				<% if  (msgRT.getH01FLGWK3().equals("3")){out.print(" DISABLED ");}%>
				<OPTION value=""</OPTION>="" 
				>
					<%
      					optCN19.initRow();

       					while (optCN19.getNextRow()) {
           					if (optCN19.getFlag().equals("")) {
           						out.println(optCN19.getRecord());
               					}
           					}
					%>
				</SELECT>
				<% }; %>

				</td>
				<td nowrap width="5%">
				</td>
				<td nowrap width="45%">
				</td>
			</tr>

			<% if((msgRTC.getE02DPJF04().equals("1"))||(msgRTC.getE02DPJF05().equals("1"))){%>	
			<tr id="trclear">
				<% if(msgRTC.getE02DPJF04().equals("1")){%>
				<td nowrap width="20%">
				    <div align="right">Destino Fondos:</div>
				</td>
				
				<td nowrap width="40%" align="left">
					<SELECT name="E01PLTDST"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					<OPTION value=""</OPTION>=""
					>
					<%
      					optCN38.initRow();
       					while (optCN38.getNextRow()) {
           					if (optCN38.getFlag().equals("")) {
           						out.println(optCN38.getRecord());
               					}
           					}
					%>
				   </SELECT>
				 </td>  
				<% }; %>

				<% if(msgRTC.getE02DPJF05().equals("1")){%>
				<td nowrap width="20%">
				    <div align="right">Origen de Fondos:</div>
				<td>    
				<td nowrap width="40%" align="left">
					<SELECT name="E01PLTORI"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					<OPTION value=""</OPTION>=""
					>
					<%
      					optCN30.initRow();
       					while (optCN30.getNextRow()) {
           					if (optCN30.getFlag().equals("")) {
           						out.println(optCN30.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<% }; %>
			</tr>
		<% }; %>	
			
		</table>
		</td>
      </tr>

	<% if(!msgRTC.getE02DPJC00().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<% if(!msgRTC.getE02DPJC00().equals("0")){%>
			<tr id="trclear">
				<td width="20%">
               <div align="right">Coment. Destino Fondos:</div>
				</td>
				<td width="80%">
                
				<TEXTAREA name="E02DPAC00" rows="2" cols="84" value="<%= msgRTC.getE02DPAC00().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}  %>
				> </TEXTAREA>
       			</td>
			</tr>
		<% } %>
		</table>
		</td>
      </tr>
    <% } %> 

	<% if((!msgRTC.getE02DPJF07().equals("0"))||(!msgRTC.getE02DPJC01().equals("0"))){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJF07().equals("0")){%>
				<td width="10%">
				<div align="left">Plazo/Tiempo:</div>
				</td>
				<td width="12%">
        	          <INPUT type="text" name="E01PLTPLZ" size="4" maxlength="3"
        	        <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					value="<%= msgList.getE01PLTPLZ().trim()%>">
					<SELECT name="E01PLTUPL"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="1"
						<%if (msgList.getE01PLTUPL().equals("1")) { out.print("selected"); }%>>Dia(s)</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTUPL().equals("2")) { out.print("selected"); }%>>Mes(es)</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTUPL().equals("3")) { out.print("selected"); }%>>Anual(es)</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTUPL().equals("4")) { out.print("selected"); }%>>Otros</OPTION>	
				</SELECT>
                
				</td>
				<% } %>

				<% if(!msgRTC.getE02DPJC01().equals("0")){%>

				<td nowrap align="right" width="29%">Comentarios:</td>
				<td nowrap align="right" width="45%">
				
				<TEXTAREA name="E02DPAC01" rows="2" cols="70" value="<%= msgRTC.getE02DPAC01().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				> </TEXTAREA>
				
				</td>
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>

      <% if(!msgRTC.getE02DPJC02().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="9%">
				</td>
				<td width="22%">
				</td>
				<% if(!msgRTC.getE02DPJC02().equals("0")){%>
				<td nowrap align="right" width="20%">
				Especifique otra forma de Plazo:</td>
				<td nowrap align="right" width="49%">
				<TEXTAREA name="E02DPAC02" rows="2" cols="70" value="<%= msgRTC.getE02DPAC02().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				></TEXTAREA>
				</td>
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
      <tr >
     <% } %> 
      
	<% if((!msgRTC.getE02DPJF06().equals("0"))||(!msgRTC.getE02DPJC03().equals("0"))){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJF06().equals("0")){%>

				<td width="15%">
				<div align="left">Forma de pago - Capital:</div>
				</td>
				<td width="9%">
				<SELECT name="E01PLTCCA" onchange="selK();"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCCA().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCCA().equals("3")) { out.print("selected"); }%>>Bimestral</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCCA().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCCA().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCCA().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTCCA().equals("7")) { out.print("selected"); }%>>Al Vencimiento</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTCCA().equals("8")) { out.print("selected"); }%>>Irregular</OPTION>
				</SELECT>
				</td>
				<% } %>
				
				<% if(!msgRTC.getE02DPJC03().equals("0")){%>
				<td nowrap align="right" width="28%">Comentarios:</td>
				<td nowrap align="right" width="45%">
				<TEXTAREA name="E02DPAC03" rows="2" cols="70" value="<%= msgRTC.getE02DPAC03().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				></TEXTAREA>
				</td>
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>


	<% if(!msgRTC.getE02DPJC04().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="9%">
				</td>
				<td width="23%">
				</td>
				<% if(!msgRTC.getE02DPJC04().equals("0")){%>
				<td nowrap align="right" width="19%">
				  Especifique otra forma Pago Cap.:
				</td>
				<td nowrap align="right" width="49%">
				<TEXTAREA name="E02DPAC04" rows="2" cols="70" value="<%= msgRTC.getE02DPAC04().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				></TEXTAREA>
				</td>
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 
       
    <% if((!msgRTC.getE02DPJF08().equals("0"))||(!msgRTC.getE02DPJC05().equals("0"))){%>
        <tr>
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJF08().equals("0")){%>
				<td width="15%">
					<div align="left">Forma de pago - Interes:</div>
				</td>
				<td width="6%">
					<SELECT name="E01PLTCIN" 
				      <% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCIN().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCIN().equals("3")) { out.print("selected"); }%>>Bimestral</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCIN().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCIN().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCIN().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTCCA().equals("7")) { out.print("selected"); }%>>Al Vencimiento</OPTION>		
					<OPTION value="8"
						<%if (msgList.getE01PLTCCA().equals("8")) { out.print("selected"); }%>>Irregular</OPTION>
					</SELECT>
				</td>
                <% } %>
				
				<% if(!msgRTC.getE02DPJC05().equals("0")){%>
				<td nowrap align="right" width="31%">Comentarios:</td>
				<td nowrap align="right" width="45%">
				   <TEXTAREA name="E02DPAC05" rows="2" cols="70"  value="<%= msgRTC.getE02DPAC05().trim()%>"
				   <% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				></TEXTAREA>
				</td>
				<% } %>
			</tr>
		</table>
		<td>
      </tr> 
     <% } %>
      
     	<% if(!msgRTC.getE02DPJC06().equals("0")){%>
     	<tr>
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJC06().equals("0")){%>
				<td width="9%">
				</td>
				<td width="22%">
				</td>
				<td nowrap align="right" width="20%">
					Especifique Forma Pago Int.:
				</td>
				<td nowrap align="right" width="49%">
				<TEXTAREA name="E02DPAC06" rows="2" cols="70" value="<%= msgRTC.getE02DPAC06().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				>
				</TEXTAREA>
				</td>
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 
      
      
      
	<% if(!msgRTC.getE02DPJF09().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJF09().equals("0")){%>
				<td width="15%">
				<div align="left">Forma de Cobro Interes:</div>
				</td>
				<td width="9%">
				<SELECT name="E01PLTCBI"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="1"
						<%if (msgList.getE01PLTCBI().equals("1")) { out.print("selected"); }%>>Anticipado</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCBI().equals("2")) { out.print("selected"); }%>>Vencimiento</OPTION>

				</SELECT></td>
				<td nowrap align="right" width="23%">
				</td>
				<% } %>
				<td nowrap width="55%"></td>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 


	<% if(!msgRTC.getE02DPJC11().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
		      <% if(!msgRTC.getE02DPJC11().equals("0")){%>
				<td width="15%">
				<div align="right">Descripcion Mercaderia :</div>
				</td>
				
				<td width="10%" align="left">
				  <TEXTAREA name="E02DPAC11" rows="2" cols="70" value="<%= msgRTC.getE02DPAC11().trim()%>"
				  <% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}  %>
				  > </TEXTAREA>
				</td>
				
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>
      

	 <% if(!msgRTC.getE02DPJC12().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
		      <% if(!msgRTC.getE02DPJC12().equals("0")){%>
				<td width="15%">
				<div align="right">Forma Pago(%) :</div>
				</td>
				
				<td width="10%" align="left">
				  <TEXTAREA name="E02DPAC12" rows="2" cols="70" value="<%= msgRTC.getE02DPAC12().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}  %>
				> </TEXTAREA>
				</td>
				
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
     <% } %> 

   <% if(!msgRTC.getE02DPJC13().equals("0")){%>				
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
		      <% if(!msgRTC.getE02DPJC13().equals("0")){%>				
				<td width="15%">
				<div align="right">Al Beneficiario :</div>
				</td>
				
				<td width="10%" align="left">
				   <TEXTAREA name="E02DPAC13" rows="2" cols="70" value="<%= msgRTC.getE02DPAC13().trim()%>"
				    <% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}  %>
				    > </TEXTAREA>
				</td>
				
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>
         
       <% if((!msgRTC.getE02DPJF10().equals("0"))||(!msgRTC.getE02DPJF11().equals("0"))){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
			  <% if(!msgRTC.getE02DPJF10().equals("0")){%>
				<td width="15%">
				<div align="left">Tasa Propuesta :</div>
				</td>
				<td width="10%">
				   <A name="Miscelaneos1">
				   <INPUT type="TEXT" name="E01PLTRTE" size="10" maxlength="9"
					value="<%= msgList.getE01PLTRTE().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				   </A>
				</td>
				<% }; %>
				
    		  <% if(!msgRTC.getE02DPJF11().equals("0")){%>
				<td nowrap width="20%" align="right">
                	<div align="right">Tasa Cliente/Producto :</div>
				</td>
				<td nowrap width="55%">
				    <INPUT type="TEXT" name="E01XXXRTE" size="14" maxlength="9"
					value="<%= msgList.getE01XXXRTE().trim()%>" readonly>
				</td>
				<% }; %>
			</tr>
		</table>
		</td>
      </tr>
     <% }; %> 
      
     <% if(!msgRTC.getE02DPJF12().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
			  <% if(!msgRTC.getE02DPJF12().equals("0")){%>
				<td width="15%">
				<div align="left">Comision Apertura :</div>
				</td>
				<td width="10%">
				   <INPUT type="TEXT" name="E01PLTCOA" size="10" maxlength="9"
					value="<%= msgList.getE01PLTCOA().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				</td>
				<td nowrap width="20%" align="right">
				</td>
				<td nowrap width="55%">
				</td>
				<% }; %>
			</tr>
		</table>
		</td>
      </tr>
     <% }; %> 
      
  <% if(!msgRTC.getE02DPJF13().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
			  <% if(!msgRTC.getE02DPJF13().equals("0")){%>
				<td width="15%">
				<div align="left">Comision Utilizacion :</div>
				</td>
				<td width="10%">
				   <INPUT type="TEXT" name="E01PLTCOU" size="10" maxlength="9"
					value="<%= msgList.getE01PLTCOU().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				</td>
				<td nowrap width="20%" align="right">
				</td>
				<td nowrap width="55%">
				</td>
				<% }; %>
			</tr>
		</table>
		</td>
      </tr>
    <% }; %> 
      
  <% if(!msgRTC.getE02DPJF14().equals("0")){%>  
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
			  <% if(!msgRTC.getE02DPJF14().equals("0")){%>
				<td width="15%">
				<div align="left">Comision Aceptacion :</div>
				</td>
				<td width="10%">
				   <INPUT type="TEXT" name="E01PLTCOC" size="10" maxlength="9"
					value="<%= msgList.getE01PLTCOC().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					>
				</td>
				<td nowrap width="20%" align="right">
				</td>
				<td nowrap width="55%">
				</td>
				<% }; %>
			</tr>
		</table>
		</td>
      </tr>
	<% }; %>  
      
    <% if(!msgRTC.getE02DPJC07().equals("0")){%>    
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
		      <% if(!msgRTC.getE02DPJC07().equals("0")){%>
				<td width="15%">
				<div align="right">Comisión o Girado:</div>
				</td>
				
				<td width="10%" align="left">
				  <TEXTAREA name="E02DPAC07" rows="2" cols="70" value="<%= msgRTC.getE02DPAC07().trim()%>"
				 <% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				 >
				 </TEXTAREA>
				</td>				
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
    <% } %>  
      
   <% if(!msgRTC.getE02DPJC08().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
		     <% if(!msgRTC.getE02DPJC08().equals("0")){%>
				<td width="15%">
				<div align="right">Fuente de Repago:</div>
				</td>
				
				<td width="10%" align="left">
				 <TEXTAREA name="E02DPAC08" rows="2" cols="70" value="<%= msgRTC.getE02DPAC08().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				>
				</TEXTAREA>
				</td>
				
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
    <% } %>  
      
      
	<% if((!msgRTC.getE02DPJF16().equals("0"))||(!msgRTC.getE02DPJC09().equals("0"))){%>
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJF16().equals("0")){%>
				<td width="15%">
			<div align="right">Garantías/Aval:</div>
			</td>
				<td width="10%">
				<SELECT name="E01PLTGAR"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""
						<%if (msgList.getE01PLTGAR().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="0"
						<%if (msgList.getE01PLTGAR().equals("0")) { out.print("selected"); }%>>Ninguna</OPTION>
					<OPTION value="1"
						<%if (msgList.getE01PLTGAR().equals("1")) { out.print("selected"); }%>>Garantías</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTGAR().equals("2")) { out.print("selected"); }%>>Avales</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTGAR().equals("3")) { out.print("selected"); }%>>Garantías/Avales</OPTION>

				</SELECT></td>
				<% }; %>
				
				<% if(!msgRTC.getE02DPJC09().equals("0")){%>
			<td nowrap align="left" width="27%">
			<div align="right">Comentarios:</div>
			</td>
			<td nowrap align="right" width="48%">
				<TEXTAREA name="E02DPAC09" rows="2" cols="70" value="<%= msgRTC.getE02DPAC09().trim()%>"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				>
				</TEXTAREA>
				</td>
				<% }; %>
			</tr>
		</table>
		</td>
	<% }; %>	
		
		<tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<td width="15%">
                   <div align="right"></div>
				</td>
				<td width="10%">
				</td>
				<td nowrap width="20%" align="right">
                	<div align="right"></div>
				</td>
				<td nowrap width="55%">
				</td>
			</tr>
		</table>
		</td>
      </tr>
	
		<% if(!msgRTC.getE02DPJF17().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJF17().equals("0")){%>
				<td width="10%">
				<div align="left">Cantidad de Cuotas:</div>
				</td>
				<td width="12%">
        	        <INPUT type="text" name="E01PLTNCU" size="4" maxlength="3"
        	        <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					value="<%= msgList.getE01PLTNCU().trim()%>">               
				</td>
				<td nowrap align="right" width="29%"></td>
				<td nowrap align="right" width="45%"></td>
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>
      
	<% if(!msgRTC.getE02DPJF18().equals("0")){%>
      <tr > 
        <td width="1275">
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr id="trclear">
				<% if(!msgRTC.getE02DPJF18().equals("0")){%>

				<td width="15%">
				<div align="left">Frecuencia Extraordinaria:</div>
				</td>
				<td width="9%">
				<SELECT name="E01PLTCCX" onchange="selK();"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""></OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCCX().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCCX().equals("3")) { out.print("selected"); }%>>Bimestral</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCCX().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCCX().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCCX().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTCCX().equals("7")) { out.print("selected"); }%>>Al Vencimiento</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTCCX().equals("8")) { out.print("selected"); }%>>Irregular</OPTION>
				</SELECT>
				</td>
				
				<td nowrap align="right" width="28%">Monto Extraordinario</td>
				<td nowrap align="left" width="45%"><INPUT type="text"
					name="E01PLTAMX" size="20" maxlength="20"
					value="<%= msgList.getE01PLTAMX().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%>
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>>
				</td>
				<% } %>
			</tr>
		</table>
		</td>
      </tr>
      <% } %>
	
   <% if(!msgRTC.getE02DPJF21().equals("0")){%>
	<tr>
		<td width="1275">
	   		<table class="tableinfo">
    		<tr>
    			<TD>
    				<h4>Numero de Cuenta</h4>      
		    	</TD>
		    </tr>    
		    <tr> 
    			<td nowrap> 
        			<table cellpadding=2 cellspacing=0 width="100%" border="0">
          				<tr id="trdark"> 
          				   <td nowrap> 
              					<div align="center">Referencia</div>
				            </td>
          				  
            				<td nowrap width="33%"> 
              					<div align="center"></div>
            				</td>
            				<td nowrap width="15%"> 
              					<div align="center"></div>
            				</td>
				            <td nowrap width="22%"> 
              					<div align="center"></div>
            				</td>
				            <td nowrap> 
             					<div align="center"></div>
				            </td>
						</tr>
 
						<td nowrap width="16%" > 
              					<div align="center"> 
                					<input type="text" name="E01PLTCCR" size="12" maxlength="12"  value="<%= msgList.getE01PLTCCR().trim()%>"
                 						oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].value,'',document.forms[0].E01CUSCUN.value,'','RT'); return false;">
								</div>
				        </td>
 
						<tr id="trclear"> 
            				<td nowrap width="33%" > 
              					<div align="center" nowrap> 
                  					<input type="hidden" name="E01PAGOPE" value="<%= msgList.getE01PAGOPE().trim()%>" size="3" maxlength="3">
                  					<input type="hidden" name="E01PLTGCR" value="<%= msgList.getE01PLTGCR().trim()%>">
                  					<input type="hidden" name="E01PAGCON" size="25" maxlength="25" readonly value="<%= msgList.getE01PAGCON().trim()%>"
									oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01PLPBNK.value,'','E01PLTGCR','E01PAGOPE','<%= msgList.getE01PLTACD().trim()%>'); return false;">
								</div>
				            </td>
				            
            				<td nowrap width="15%" > 
              					<div align="center"> 
                  					<input type="hidden" name="E01PLPBNK"  value="<%= msgRT.getE01PLPBNK().trim()%>" size="2" maxlength="2">
				 				</div>
							</td>
							
            				<td nowrap width="22%" > 
                 				<div align="center"> 
                					<input type="hidden" name="E01PLPBRN" size="3" maxlength="3" value="<%= msgRT.getE01PLPBRN().trim()%>"
                 						oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01PLPBNK.value,'','','',''); return false;"> 
				               </div>
				            </td>
            				<td nowrap width="14%" > 
              					<div align="center"> 
                					<input type="hidden" name="E01PLTCCY99" size="3" maxlength="3" value="<%= msgList.getE01PLTCCY().trim()%>"
						                 oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01PLPBNK.value,'','','',''); return false;">
								</div>
							</td>
            				
			          </tr>
						<tr id="trdark"> 
            				<td nowrap width="33%" >&nbsp;</td>
            				<td nowrap width="15%" >&nbsp;</td>
            				<td nowrap width="22%" >&nbsp;</td>
           		 			<td nowrap width="14%" >&nbsp;</td>
            				<td nowrap width="16%" >&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			</table>		
		</td>
	</tr>
	 <% } %>   
	 
    <% if(!msgRTC.getE02DPJF19().equals("0")){%>
	<tr>
		<td width="1275">
	   		<table class="tableinfo">
    		<tr>
    			<TD>
    				<h4>Comisiones</h4>      
		    	</TD>
		    </tr>    
		    
			</table>		
		</td>
	</tr>
	<% } %>
	   
	<% if(!msgRTC.getE02DPJF20().equals("0")){%>
	<tr>
		<td width="1275">
	   		<table class="tableinfo">
    		<tr>
    			<TD>
    				<h4>Linea Credito</h4>      
		    	</TD>
		    </tr>    
		    
			</table>		
		</td>
	</tr>
	<% } %> 
	 
</table>	     

<div id="Others" style="display:none">
<A NAME="Otros">
<h4>Otros</h4>
<table class="tableinfo">
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="20%" align="right">Otros:</td>
				<td nowrap width="80%" align="left">
				<SELECT name="E01PLTOTH" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNP0.initRow();
       					while (optCNP0.getNextRow()) {
           					if (optCNP0.getFlag().equals("")) {
           						out.println(optCNP0.getRecord());
               					}
           					}
					%>
				</SELECT>
				    
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="20%" align="right">Motivo:
				<td nowrap width="80%" align="left">
				<TEXTAREA name="E02DPAC10" rows="2" cols="66"
					value="<%= msgRTC.getE02DPAC10().trim()%>"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>>
				</TEXTAREA>
			</tr>
			<tr id="trclear">
				<td nowrap width="20%" align="right"></td>
				<td nowrap width="80%" align="left"></td>
			</tr>
		</table>
		</td>
      </tr>
    </table>
</div>


<div id="Agricola" style="display:none">

<A NAME="agricola">

<h4>Agrícola</h4>
<table class="tableinfo">
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="10%">
				<div align="right">Número Registro:</div>
				</td>
				<td nowrap width="10%" align="left">
		            <input type=TEXT name="E01DPZTNR" size=17 maxlength=16  value="<%= msgList.getE01DPZTNR().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
				<div align="right">Tipo Registro:</div>
				</td>
				<td nowrap width="50%">
				<SELECT name="E01DPZTRE" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPT.initRow();
       					while (optCNPT.getNextRow()) {
           					if (optCNPT.getFlag().equals("")) {
           						out.println(optCNPT.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="10%">
				<div align="right">Especifique:</div>
				</td>
				<td nowrap width="98%" align="left">
		            <input type=TEXT name="E01DPZCOM" size=26 maxlength=26  value="<%= msgList.getE01DPZCOM().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="1%">
				</td>
				<td nowrap width="1%">
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="10%">
				<div align="right">Fec.Vcto.Reg.:</div>
				</td>
				<td nowrap width="10%" align="left">
				
			<% if(!msgRT.getH01FLGWK3().equals("3")){ %> 
			<a href="javascript:DatePicker(document.forms[0].E01DPZFA1,document.forms[0].E01DPZFA2,document.forms[0].E01DPZFA3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
			<% } %>				
		            
		            
		            <input type=TEXT name="E01DPZFA1" size=3 maxlength=2   value="<%= msgList.getE01DPZFA1().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
		            <input type=TEXT name="E01DPZFA2" size=3 maxlength=2   value="<%= msgList.getE01DPZFA2().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
		            <input type=TEXT name="E01DPZFA3" size=3 maxlength=2  value="<%= msgList.getE01DPZFA3().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
				<div align="right">Municipio:</div>
				</td>
				<td nowrap width="50%">
				<INPUT type="text" name="E01DPZMUN" size="3" maxlength="4" readonly value="<%= msgList.getE01DPZMUN().trim()%>"> 
				<INPUT type="text" name="D01DPZMUN" size="30" maxlength="30" readonly >
				<A href="javascript:GetCodeDescCNOFC('E01DPZMUN','D01DPZMUN','85')">
				<IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0"></A>
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right">Proveniencia Fondos (P/F):</div>
				</td>
				<td nowrap align="left" width="19%">
				<SELECT name="E01DPZPRF"
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""
						<%if (msgList.getE01DPZPRF().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="P"
						<%if (msgList.getE01DPZPRF().equals("P")) { out.print("selected"); }%>>P</OPTION>
					<OPTION value="F"
						<%if (msgList.getE01DPZPRF().equals("F")) { out.print("selected"); }%>>F</OPTION>
				</SELECT>
				</td>
				<td nowrap width="28%">
				<div align="right">Plazo del Crédito: 
				<SELECT name="E01DPZPLA"
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
					>
					<OPTION value=""
						<%if (msgList.getE01DPZPLA().equals("")) { out.print("selected"); }%>></OPTION>
					<OPTION value="C"
						<%if (msgList.getE01DPZPLA().equals("C")) { out.print("selected"); }%>>C</OPTION>
					<OPTION value="M"
						<%if (msgList.getE01DPZPLA().equals("M")) { out.print("selected"); }%>>M</OPTION>
					<OPTION value="L"
						<%if (msgList.getE01DPZPLA().equals("L")) { out.print("selected"); }%>>L</OPTION>
				</SELECT>
				</div>
								
				</td>
				<td nowrap width="25%">
				<div align="left">Nro. Dias:<INPUT type="TEXT" name="E01DPZNDD"
					size="4" maxlength="4" 
					value="<%= msgList.getE01DPZNDD().trim()%>"
					<% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
					></div>
				
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="15%">
				<div align="right">Tasa Comisión:</div>
				</td>
				<td nowrap width="15%" align="left">
		            <input type=TEXT name="E01DPZTCO" size=10 maxlength=10  value="<%= msgList.getE01DPZTCO().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="15%">
				<div align="right">Nombre Explotación:</div>
				</td>
				<td nowrap width="5%">
		            <input type=TEXT name="E01DPZEXP" size=25 maxlength=25  value="<%= msgList.getE01DPZEXP().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="1">
			<tr id="trclear">
				<td nowrap width="10%" align="left">
				<div align="left">Rubro</div>
				</td>
				<td nowrap width="35%">
				<div align="left">Uso</div>
				</td>
				<td nowrap width="10%">
				<div align="left">Cant</div>
				</td>
				<td nowrap width="15%">
				<div align="left">Unidad</div>
				</td>
				<td nowrap width="15%">
				<div align="left">SubSector</div>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">

				<SELECT name="E01DPPRB0" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPR.initRow();
       					while (optCNPR.getNextRow()) {
           					if (optCNPR.getFlag().equals("")) {
           						out.println(optCNPR.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR0" size=26 maxlength=26  value="<%= msgList.getE01DPPUR0().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR0" size=4 maxlength=4  value="<%= msgList.getE01DPPCR0().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPDR0" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPU.initRow();
       					while (optCNPU.getNextRow()) {
           					if (optCNPU.getFlag().equals("")) {
           						out.println(optCNPU.getRecord());
               					}
           					}
					%>
				</SELECT>
		            
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPSB0" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPS.initRow();
       					while (optCNPS.getNextRow()) {
           					if (optCNPS.getFlag().equals("")) {
           						out.println(optCNPS.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
				<SELECT name="E01DPPRB1" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPR.initRow();
       					while (optCNPR.getNextRow()) {
           					if (optCNPR.getFlag().equals("")) {
           						out.println(optCNPR.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR1" size=26 maxlength=26  value="<%= msgList.getE01DPPUR1().trim()%>">
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR1" size=4 maxlength=4 onKeypress="enterInteger()" value="<%= msgList.getE01DPPCR1().trim()%>">
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPDR1" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPU.initRow();
       					while (optCNPU.getNextRow()) {
           					if (optCNPU.getFlag().equals("")) {
           						out.println(optCNPU.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPSB1" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPS.initRow();
       					while (optCNPS.getNextRow()) {
           					if (optCNPS.getFlag().equals("")) {
           						out.println(optCNPS.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
				<SELECT name="E01DPPRB2" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPR.initRow();
       					while (optCNPR.getNextRow()) {
           					if (optCNPR.getFlag().equals("")) {
           						out.println(optCNPR.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR2" size=26 maxlength=26  value="<%= msgList.getE01DPPUR2().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR2" size=4 maxlength=4 value="<%= msgList.getE01DPPCR2().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPDR2" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPU.initRow();
       					while (optCNPU.getNextRow()) {
           					if (optCNPU.getFlag().equals("")) {
           						out.println(optCNPU.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPSB2" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPS.initRow();
       					while (optCNPS.getNextRow()) {
           					if (optCNPS.getFlag().equals("")) {
           						out.println(optCNPS.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
				<SELECT name="E01DPPRB3" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPR.initRow();
       					while (optCNPR.getNextRow()) {
           					if (optCNPR.getFlag().equals("")) {
           						out.println(optCNPR.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR3" size=26 maxlength=26  value="<%= msgList.getE01DPPUR3().trim()%>"
		            				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR3" size=4 maxlength=4  value="<%= msgList.getE01DPPCR3().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterInteger()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPDR3" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPU.initRow();
       					while (optCNPU.getNextRow()) {
           					if (optCNPU.getFlag().equals("")) {
           						out.println(optCNPU.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPSB3" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPS.initRow();
       					while (optCNPS.getNextRow()) {
           					if (optCNPS.getFlag().equals("")) {
           						out.println(optCNPS.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
				<SELECT name="E01DPPRB4" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPR.initRow();
       					while (optCNPR.getNextRow()) {
           					if (optCNPR.getFlag().equals("")) {
           						out.println(optCNPR.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR4" size=26 maxlength=26  value="<%= msgList.getE01DPPUR4().trim()%>"
		            <% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR4" size=4 maxlength=4 value="<%= msgList.getE01DPPCR4().trim()%>"
		            <% if (!msgRT.getH01FLGWK3().equals("3")){out.print("onkeypress=enterDecimal()");}%> 
					<% if(msgRT.getH01FLGWK3().equals("3")){out.print("readonly");}%>
		            >
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPDR4" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPU.initRow();
       					while (optCNPU.getNextRow()) {
           					if (optCNPU.getFlag().equals("")) {
           						out.println(optCNPU.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPSB4" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPS.initRow();
       					while (optCNPS.getNextRow()) {
           					if (optCNPS.getFlag().equals("")) {
           						out.println(optCNPS.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
			</tr>
			<tr id="trclear">
				<td nowrap width="10%" align="left">
				<SELECT name="E01DPPRB5" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPR.initRow();
       					while (optCNPR.getNextRow()) {
           					if (optCNPR.getFlag().equals("")) {
           						out.println(optCNPR.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="35%">
		            <input type=TEXT name="E01DPPUR5" size=26 maxlength=26  value="<%= msgList.getE01DPPUR5().trim()%>">
				</td>
				<td nowrap width="10%">
		            <input type=TEXT name="E01DPPCR5" size=4 maxlength=4 onKeypress="enterInteger()" value="<%= msgList.getE01DPPCR5().trim()%>">
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPDR5" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPU.initRow();
       					while (optCNPU.getNextRow()) {
           					if (optCNPU.getFlag().equals("")) {
           						out.println(optCNPU.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
				<td nowrap width="15%">
				<SELECT name="E01DPPSB5" 
				<% if(msgRT.getH01FLGWK3().equals("3")){out.print("DISABLED");}%>
				<OPTION value=""</OPTION>=""
				>
					<%
      					optCNPS.initRow();
       					while (optCNPS.getNextRow()) {
           					if (optCNPS.getFlag().equals("")) {
           						out.println(optCNPS.getRecord());
               					}
           					}
					%>
				</SELECT>
				</td>
			</tr>
		</table>
		</td>
      </tr>

    </table>
</div>

<div id="Condiciones" style="display:none">
<A NAME="condiciones">
<h4>Cambio Condiciones</h4>
<table class="tableinfo">
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%">
				</td>
				<td nowrap width="20%" align="left">CONDICIONES ORIGINALES
				</td>
				<td nowrap width="20%">
				<div align="right">CONDICIONES APROBACION</div>
				</td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>
      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%" align="right">
				Plazo: Cantidad/Tiempo:</td>
				<td nowrap width="20%" align="left">
				
				<INPUT type="text" name="E01PLTPLZ0" size="4" maxlength="3" READONLY
					value="<%= msgList.getE01PLTPLZ().trim()%>">
				<SELECT
					name="E01PLTUPL0" DISABLED >
					<OPTION value="1"
						<%if (msgList.getE01PLTUPL().equals("1")) { out.print("selected"); }%>>Dia</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTUPL().equals("2")) { out.print("selected"); }%>>Mes</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTUPL().equals("3")) { out.print("selected"); }%>>A&ntilde;o</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTUPL().equals("4")) { out.print("selected"); }%>>Otros</OPTION>
				</SELECT></td>
				
				<td nowrap width="20%">
       	          <INPUT type="text" name="E01PLTPAP" size="4" maxlength="3" readonly value="<%= msgList.getE01PLTPAP().trim()%>">
				<SELECT name="E01PLTUPA" DISABLED>
					<OPTION value="1"
						<%if (msgList.getE01PLTUPA().equals("1")) { out.print("selected"); }%>>Diario</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTUPA().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTUPA().equals("3")) { out.print("selected"); }%>>Bimensual</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTUPA().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTUPA().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTUPA().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTUPA().equals("7")) { out.print("selected"); }%>>Al
					Vencimiento</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTUPA().equals("8")) { out.print("selected"); }%>>Otros</OPTION>
				</SELECT></td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802" align="right">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%">
				</td>
				<td nowrap width="20%" align="left">
				</td>
				<td nowrap width="20%">
				</td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802" align="right">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%" align="right">Tasa:
				</td>
				<td nowrap width="20%" align="left">
				<INPUT type="TEXT" name="E01PLTRTE0"
					size="10" maxlength="9" 
					value="<%= msgList.getE01PLTRTE().trim()%>" READONLY >
				</td>
				<td nowrap width="20%">
				<INPUT type="TEXT" name="E01PLTRAP" size="10" maxlength="9"  readonly
					value="<%= msgList.getE01PLTRAP().trim()%>" >
				</td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802" align="right">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%" align="right">Forma de Pago Capital:
				</td>
				<td nowrap width="20%" align="left">
				<SELECT name="E01PLTCIN0" DISABLED >
					<OPTION value="1"
						<%if (msgList.getE01PLTCIN().equals("1")) { out.print("selected"); }%>>Diario</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCIN().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCIN().equals("3")) { out.print("selected"); }%>>Bimensual</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCIN().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCIN().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCIN().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTCIN().equals("8")) { out.print("selected"); }%>>Otros</OPTION>
				</SELECT></td>
				<td nowrap width="20%">
                
				<SELECT name="E01PLTCAP" DISABLED >
					<OPTION value="1"
						<%if (msgList.getE01PLTCAP().equals("1")) { out.print("selected"); }%>>Diario</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCAP().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCAP().equals("3")) { out.print("selected"); }%>>Bimensual</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCAP().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCAP().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCAP().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTCAP().equals("7")) { out.print("selected"); }%>>Al Vencimiento</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTCAP().equals("8")) { out.print("selected"); }%>>Otros</OPTION>
				</SELECT>

				</td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802" align="right">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%" align="right">Forma de Pago Intereses:
				</td>
				<td nowrap width="20%" align="left">
				<SELECT name="E01PLTCIN1" DISABLED >
					<OPTION value="1"
						<%if (msgList.getE01PLTCIN().equals("1")) { out.print("selected"); }%>>Diario</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTCIN().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTCIN().equals("3")) { out.print("selected"); }%>>Bimensual</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTCIN().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTCIN().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTCIN().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTCIN().equals("8")) { out.print("selected"); }%>>Otros</OPTION>
				</SELECT></td>
				<td nowrap width="20%">
                
				<SELECT name="E01PLTIAP" DISABLED >
					<OPTION value="1"
						<%if (msgList.getE01PLTIAP().equals("1")) { out.print("selected"); }%>>Diario</OPTION>
					<OPTION value="2"
						<%if (msgList.getE01PLTIAP().equals("2")) { out.print("selected"); }%>>Mensual</OPTION>
					<OPTION value="3"
						<%if (msgList.getE01PLTIAP().equals("3")) { out.print("selected"); }%>>Bimensual</OPTION>
					<OPTION value="4"
						<%if (msgList.getE01PLTIAP().equals("4")) { out.print("selected"); }%>>Trimestral</OPTION>
					<OPTION value="5"
						<%if (msgList.getE01PLTIAP().equals("5")) { out.print("selected"); }%>>Semestral</OPTION>
					<OPTION value="6"
						<%if (msgList.getE01PLTIAP().equals("6")) { out.print("selected"); }%>>Anual</OPTION>
					<OPTION value="7"
						<%if (msgList.getE01PLTIAP().equals("7")) { out.print("selected"); }%>>Al Vencimiento</OPTION>
					<OPTION value="8"
						<%if (msgList.getE01PLTIAP().equals("8")) { out.print("selected"); }%>>Otros</OPTION>
				</SELECT>
								
				</td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802" align="right">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%" align="right">Forma de Cobro Intereses:
				</td>
				<td nowrap width="20%" align="left">
				<SELECT name="E01PLTCBI1" DISABLED>
					<OPTION value="1"
						<%if (msgList.getE01PLTCBI().equals("1")) { out.print("selected"); }%>>Anticipado</OPTION>
					<OPTION value="0"
						<%if (msgList.getE01PLTCBI().equals("2")) { out.print("selected"); }%>>Vencimiento</OPTION>
				</SELECT>
				</td>
				<td nowrap width="20%">
				<SELECT name="E01PLTCBI0" DISABLED>
					<OPTION value="1"
						<%if (msgList.getE01PLTCBI().equals("1")) { out.print("selected"); }%>>Anticipado</OPTION>
					<OPTION value="0"
						<%if (msgList.getE01PLTCBI().equals("2")) { out.print("selected"); }%>>Vencimiento</OPTION>

				</SELECT></td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802">
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%" align="right">Observaciones:
				</td>
				<td nowrap width="20%" align="left">
				</td>
				<td nowrap width="20%">
				</td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>

      <tr > 
        <td width="802">
		<table cellspacing="0" cellpadding="2" width="100%" border="0">
			<tr id="trclear">
				<td nowrap width="30%" align="right">Monto:
				</td>
				<td nowrap width="20%" align="left">
				<INPUT type="text" name="E01PLTAMT0" size="20" maxlength="20" value="<%= msgList.getE01PLTMAP().trim()%>"
					 readonly >
				</td>
				<td nowrap width="20%">
				<INPUT type="text" name="E01PLTMAP" size="20" maxlength="20" value="<%= msgList.getE01PLTMAP().trim()%>"
					 readonly >
				</td>
				<td nowrap width="30%">
				</td>
			</tr>
		</table>
		</td>
      </tr>
    </table>
</div>
  
<div align="center"> 
	<% if (!msgRT.getH01FLGWK3().equals("3")) { %>
    <INPUT id="EIBSBTN" type="button" name="Submit0" value="Enviar" onclick="goConfirm('<%= userPO.getHeader16() %>','<%= userPO.getHeader23() %>','<%= userPO.getHeader22() %>','<%= userPO.getHeader21() %>')" <% if(userPO.getHeader16().equals("X")){out.print("DISABLED");} %>>
	<% } %>
	<INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader16() %>')">
</div>
          
</form>

</BODY>
</HTML>
