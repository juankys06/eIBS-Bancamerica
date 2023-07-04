<%@ page import ="datapro.eibs.master.Util" %> 
<html>
<head>
<title>Propuesta de Credito</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="EDP072101Help" class="datapro.eibs.beans.JBObjList" scope="session" />
<jsp:useBean id="EDP072601Help" class="datapro.eibs.beans.JBObjList" scope="session" />

<jsp:useBean id="msgRT" class="datapro.eibs.beans.EDP072001Message"  scope="session" />
<jsp:useBean id="msgRTC" class="datapro.eibs.beans.EDP072201Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "optLP4" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />

<jsp:useBean id="ga0726" class="datapro.eibs.beans.JBListRec" scope="session" />
<jsp:useBean id="ga0726T" class="datapro.eibs.beans.JBListRec" scope="session" />
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
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0730?SCREEN=100&CUSTOMER="+ <%=msgRT.getE01CUSCUN().trim()%>;
    	CenterNamedWindow(page,'Information',700,600,2);
}

function callCupo() {
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.credit.JSELN0110?SCREEN=1";
    	CenterNamedWindow(page,'Information',700,600,2);
}


function callSegP() {
    	page = "<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0750?SCREEN=200&RUT="+ <%=msgRT.getE01PLPNPR().trim()%>+"&DSC= ";
    														
    	CenterNamedWindow(page,'Information',700,600,2);
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



function init(){

 // asume siguiente actividad cuando hay una sola actividad para seleccionar si no es apertura
 if (document.forms[0].available != null && document.forms[0].available.value != 0) {
  } else {
		document.forms[0].TASK.value = "EDP0720_proposals_new.jsp";
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
<%--
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
--%>
 }


function goControlDoc(opt) {

	var error = 0
	var smsg = ""
	var emsg = ""
	var smsg1 = ""
	var smsg2 = ""
	var smsg3 = ""
	var emsg1 = ""
	var emsg2 = ""
	var emsg3 = ""

	if (<%=(gaCodeList.getNoResult())%> == true) {
		smsg = 'Debe adicionar un producto para esta propuesta \n\n'
		emsg = 'It should add a product for this proposal\n\n'
		error = 1;
	}

	if (document.forms[0].E01CUSCUN.value == 0) {
		smsg3 = 'Debe Asignar un numero de cliente \n\n'
		emsg3 = 'It Should Assign client number \n\n'
		error = 1;
	}

	if (document.forms[0].E01CUSNA1.value == 0) {
		smsg3 = 'Error en datos del cliente \n\n'
		emsg3 = 'Error in client data \n\n'
		error = 1;
	}

	var op = opt;  
	switch (op) {
	case  "1": 	document.forms[0].OPECOD.value = "0001";
	            document.forms[0].PARAM.value = "1-1-1";
	            //document.forms[0].E01DPPPXX.value = "0001";
	            //document.forms[0].E01DPPDXX.value = "APERTURA PROPUESTA";
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
	case  "8": 	document.forms[0].OPECOD.value = "0001";
	            document.forms[0].PARAM.value = "1-1-1";
	            //document.forms[0].E01DPPPXX.value = "0001";
	            //document.forms[0].E01DPPDXX.value = "RENOVACION PROPUESTA";
	            document.forms[0].TASK.value = "EDP0720_proposals_header_maintenance.jsp";
	 break; 
	};

	if (op != "1") {
		if (document.forms[0].E02DPAC99.value == "") { 
			smsg1 = 'Especifique comentarios de Análisis de Credito';
			emsg1 = 'Specify Credit Analysis comments';
			error = 1;
			document.forms[0].E02DPAC99.focus();	
		}

		if ("<%= msgRT.getE01DPPCOM().trim()%>" == "1") {
			if (document.forms[0].E01DPSCOM.value == "") { 
			smsg2 = 'Especifique comentarios de '+"<%= msgRT.getE01XXXEJ1().trim()%>";
			emsg2 = 'Especify comments of '+"<%= msgRT.getE01XXXEJ1().trim()%>";
			error = 1;
			}
		}
	}

	//alert(error);
	if (error  == 0){

	document.forms[0].SCREEN.value="600";
	document.forms[0].submit(); 

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
			alert('Su ingreso ha sido truncado');
		} else {
			alert('Your input has been truncated');
		}	
	}	
	if (limitField.value.length > limitNum) {
		limitField.value = limitField.value.substring(0, limitNum);
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

// TRABAJA CON CAMPOS CREADOS DINAMICAMENTE
function asigDat(name) {
	var n = name;
	if (n < 10) {
		var DPUBCHK = "document.forms[0].E01DPUB0"+n+".checked" ;
		if (eval(DPUBCHK) == true) {
			eval("document.forms[0].E01DPUB0"+n+".value = 1");
			eval("document.forms[0].E01DPDA0"+n+".value = '<%=msgRT.getE01PLPIPD().trim()%>'");
			eval("document.forms[0].E01DPDM0"+n+".value = '<%=msgRT.getE01PLPIPM().trim()%>'");
			eval("document.forms[0].E01DPDY0"+n+".value = '<%=msgRT.getE01PLPPLP().trim()%>'");
		} else {
			eval("document.forms[0].E01DPUB0"+n+".value = ''");
			eval("document.forms[0].E01DPDA0"+n+".value = ''");
			eval("document.forms[0].E01DPDM0"+n+".value = ''");
			eval("document.forms[0].E01DPDY0"+n+".value = ''");
		}
	} else {
		var DPUBCHK = "document.forms[0].E01DPUB"+n+".checked" ;
		if (eval(DPUBCHK) == true) {
			eval("document.forms[0].E01DPUB"+n+".value = 1");
			eval("document.forms[0].E01DPDA"+n+".value = '<%=msgRT.getE01PLPIPD().trim()%>'");
			eval("document.forms[0].E01DPDM"+n+".value = '<%=msgRT.getE01PLPIPM().trim()%>'");
			eval("document.forms[0].E01DPDY"+n+".value = '<%=msgRT.getE01PLPPLP().trim()%>'");
		} else {
			eval("document.forms[0].E01DPUB"+n+".value = ''");
			eval("document.forms[0].E01DPDA"+n+".value = ''");
			eval("document.forms[0].E01DPDM"+n+".value = ''");
			eval("document.forms[0].E01DPDY"+n+".value = ''");
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
  //     
%>
<H3 align="center">Nueva Propuesta de Cr&eacute;dito<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="proposals_new.jsp, EDP0720">
</H3>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0720" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="opt" VALUE="<%= userPO.getHeader16().trim()%>">
  <INPUT TYPE=HIDDEN NAME="optH" VALUE="">
  <INPUT TYPE=HIDDEN NAME="optH1" VALUE="">
  <INPUT TYPE=HIDDEN NAME="PROP" VALUE="">
  <INPUT TYPE=HIDDEN NAME="FMT" VALUE="">

  <INPUT TYPE=HIDDEN NAME="OPPRODUCT" VALUE="">

  <INPUT type=HIDDEN name="E01PLPPTY" value="<%= msgRT.getE01PLPPTY().trim()%>">
  <INPUT type=HIDDEN name="TIPPROPUESTA" value="<%= msgRT.getE01PLPPTY().trim()%>">
 
  <input type=HIDDEN name="OPECOD" value="<%= msgRT.getE01DPWOPC().trim()%>">
  <input type=HIDDEN name="PARAM" value="<%= msgRT.getE01DPWPAR().trim()%>">
  <input type=HIDDEN name="TASK" value="<%= userPO.getHeader23().trim()%>">
  <input type=HIDDEN name="E01YYYFIL" value="<%= msgRT.getE01YYYFIL().trim()%>">

  <input type=HIDDEN name="PRD" value="<%= userPO.getProdCode()%>"> 
  <input type=HIDDEN name="BNK" value="<%= userPO.getBank()%>"> 
  <input type=HIDDEN name="BRN" value="<%= userPO.getBranch()%>"> 
  <input type=HIDDEN name="RUT" value="<%= userPO.getHeader15().trim()%>"> 
  <input type=HIDDEN name="CUS" value="<%= userPO.getCusNum()%>"> 
<%--
  <input type=HIDDEN name="AGD" value="<%= msgRT.getE01PLPAGD().trim()%>">
--%>
  <input type=HIDDEN name="pos" value="<%= msgRT.getE01RECPOS().trim()%>">

	<INPUT TYPE=HIDDEN NAME="COLLCODW" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="CCOD" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="ICOD" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="ROW" VALUE="0"> 
	<INPUT TYPE=HIDDEN NAME="PRDG" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="TYPG" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="REGG" VALUE=""> 
	<INPUT TYPE=HIDDEN NAME="TGAR" VALUE=""> 
  <INPUT TYPE=HIDDEN NAME="LAN" value="<%= msgRT.getE01CNTLAN().trim()%>">

  <input type=HIDDEN name="NPR" value="<%= msgRT.getE01PLPNPR().trim()%>">
 
  <input type=HIDDEN name="E01PLPRUT" value="<%= msgRT.getE01PLPRUT().trim()%>">
  
<% if (userPO.getHeader16().equals("1" )) { %>
  <input type=HIDDEN name="E01DPPPXX" value="0001"> 
  <input type=HIDDEN name="E01DPPDXX" value="APERTURA PROPUESTA"> 
  <input type=HIDDEN name="E01PLPSRU" value="<%= msgRT.getE01PLPSRU().trim()%>">  
  <input type=HIDDEN name="E01PLPACT" value="<%= msgRT.getE01PLPACT().trim()%>"> 
<% }; %>

  <h4><A NAME="Miscelaneos"> Propuesta</h4>
 <table class="tableinfo">
    <tr > 
      <td>
		<table cellspacing="0" cellpadding="2" width="100%" border="0">

		<tr id="trdark"> 
            <td width="15%"> 
              <div align="right">Tipo de Propuesta</div>
            </td>
            
            <td width="80%" colspan="3">
            	<INPUT type="hidden" name="TYPEPROPOSAL" size="2" maxlength="2" value="<%= msgRTC.getE01COMIV5().trim()%>" readonly>
               <%-- APERTURA --%>          
  				  <input type="radio" name="E01PLPPTY9" value="1" disabled <%if(msgRT.getE01PLPPTY().equals("1")) out.print("checked");%>>
              		Solicitud 
               <%--           
              	  <input type="radio" name="E01PLPPTY9" value="7" disabled <%if(msgRT.getE01PLPPTY().equals("7")) out.print("checked");%>>
              		Otras Operaciones				
				 --%>
            </td>
           </tr>
			
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
		      	    <input type="text" name="E01PLPBNK" size="3" maxlength="2" value="" onKeypress="enterInteger()">
			        <a href="javascript:GetBank('E01PLPBNK','')">
			        <img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
				<% } else { %> 
				<input type="text" name="E01PLPBNK" size="3" maxlength="2" value="<%= msgRT.getE01PLPBNK().trim()%>" readonly>
				<% };%>
				</td>
				<td width="20%" align="right">Sucursal de Apertura:</td>
				<td width="30%">
					<input type="text" name="E01PLPBRN" size="5" maxlength="4" value="<%= msgRT.getE01PLPBRN().trim()%>" readonly>
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
            <td width="20%" align="right">Destino
		    </td>
            <td width="50%" align="left">
	   		<input type="text" name="E01PLPCN2" size="5" maxlength="4" value="<%= msgRT.getE01PLPCN2().trim()%>" 
			<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
   			>
       		
	   		<% if(!userPO.getHeader16().equals("5")) {%>
			<A href="javascript:GetCodeDescCNOFC('E01PLPCN2','E01PLPRE2','PD')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
			</A>
			<% } %>
			<INPUT type="text" name="E01PLPRE2" size="15" maxlength="15" value="<%= msgRT.getE01PLPRE2().trim()%>" readonly> 
	   		<input type="text" name="E01PLPCN3" size="5" maxlength="4" value="<%= msgRT.getE01PLPCN3().trim()%>" 
			<% if(userPO.getHeader16().equals("5")){out.print("readonly");}%>
   			>
       		
	   		<% if(!userPO.getHeader16().equals("5")) {%>
			<A href="javascript:GetCodeDescCNOFC('E01PLPCN3','E01PLPRE3','PD')"><IMG src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0">
			</A>
			<% } %>
			<INPUT type="text" name="E01PLPRE3" size="15" maxlength="15" value="<%= msgRT.getE01PLPRE3().trim()%>" readonly> 
		    </td>
           </tr>

		</table>
		</td>
    </tr>
  </table>
   <h4>Datos del Cliente</h4>
 <table class="tableinfo">
    <tr > 
      <td> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td width="20%"> 
              <div align="right">N&uacute;mero Cliente :</div>
            </td>
            <td width="30%">
            
				<% if(userPO.getHeader16().equals("1")) { %>
		            <input type=TEXT name="E01CUSCUN" size=10 maxlength=9 onKeypress="enterInteger()" value="<%= msgRT.getE01CUSCUN().trim()%>" onblur="retCustInf();" >
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
            <td width="20%" align="right"></td>
            <td width="30%"></td>
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
              <div align="right">Fecha Nacimiento/Constitución:</div>
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
			<% if ((msgRT.getE01SWMPRD().trim().equals("1")) && (!userPO.getHeader16().equals("5")))  { %>
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
				<div align="right">Mnt. Solic/Amort</div>
				</th>
				<th align=CENTER nowrap width="20%">
				<div align="right">Monto Aprobado.</div>
				</th>
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
<%} %>

  <div align="center"> 
       <INPUT id="EIBSBTN" type="button" name="Cancel" value="Cancelar" onclick="goCancel('<%= userPO.getHeader9().trim() %>')">
  </div>

  <SCRIPT language="JavaScript">

     function resizeDoc() {
      var actvTb = document.forms[0].CCOD.value;
      var dataT = document.all["dataTable"+actvTb];
       adjustEquTables(headTable1, dataTable, dataDiv1,1,false);
      }
	</SCRIPT> 
          
          
  </form>
</body>
</html>
