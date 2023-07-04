<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pago de Prestámos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<jsp:useBean id= "lnRenew" class= "datapro.eibs.beans.EDL091001Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<SCRIPT LANGUAGE="JavaScript">
 builtHPopUp();

 function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
 }
 
 function showDetail(typ) {
  var coll = document.forms[0].elements(typ);
  if (coll != null) {
    page= prefix +language + "EDL0910_ln_renewal_chg.jsp?Type="+ typ;		
    CenterWindow(page,500,400,3);
  } else alert("No existe detalle de este cargo");   
 }
 
 function UpdateField(beffield,payfield,capfield,adjfield,aftfield) {
     var befval = 0.00;
     var payval = 0.00;
     var capval = 0.00;
     var adjval = 0.00;
     var aftval = 0.00;
  
     befval = parseFloat(formatFloat(document.forms[0][beffield].value));     
     if (isNaN(befval)) befval=0.00;
     payval = parseFloat(formatFloat(document.forms[0][payfield].value));
     if (isNaN(payval)) payval=0.00;
     if (!(capfield == '')) {
     	var capval=parseFloat(formatFloat(document.forms[0][capfield].value));
      	if (isNaN(capval)) capval=0.00;
     }
     if (!(adjfield == '')) {
     	var adjval=parseFloat(formatFloat(document.forms[0][adjfield].value));
      	if (isNaN(adjval)) adjval=0.00;
     }

   	aftval = aftval = befval - payval - capval + adjval;
   	aftval = roundNumberToDecimalPlaces(aftval,2);
    document.forms[0][aftfield].value = formatCCY(""+ roundFCCY(aftval));
     
  }
 //Redondea un numero a la cantidad de decimales deseados.
function roundNumberToDecimalPlaces( number , decimalplaces ) {
    var rnum = number;
    var rlength = decimalplaces
    var newnumber = 0;
    if (rnum > 8191 && rnum < 10485) {
        rnum = rnum-5000;
        newnumber = 
Math.round(rnum*Math.pow(10,rlength))/Math.pow(10,rlength);
        newnumber = newnumber+5000;
    } else {
        newnumber = 
Math.round(rnum*Math.pow(10,rlength))/Math.pow(10,rlength);
    }
    return newnumber;
}
 
 
 function UpdateTotals() {
     var payval = 0.00;
     var capval = 0.00;
     var adjval = 0.00;
     var tmp = 0.00;
     if (trim(document.forms[0].E01PAGPRI.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01PAGPRI.value));
     payval = payval + tmp;
     <% if(lnRenew.getH01FLGWK3().equals("R")){%>
     if (trim(document.forms[0].E01PAGREA.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01PAGREA.value));
     payval = payval + tmp;
     if (trim(document.forms[0].E01CAPREA.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01CAPREA.value));      
     capval = capval + tmp;    
     if (trim(document.forms[0].E01ADJREA.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01ADJREA.value));      
     adjval = adjval + tmp;    
     <% } %>
     if (trim(document.forms[0].E01PAGINT.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01PAGINT.value));
     payval = payval + tmp;
     if (trim(document.forms[0].E01PAGMOR.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01PAGMOR.value));
     payval = payval + tmp;
     payval = payval + parseFloat(formatFloat(document.forms[0].E01PAGIMP.value));
     payval = payval + parseFloat(formatFloat(document.forms[0].E01PAGCOM.value));
     payval = payval + parseFloat(formatFloat(document.forms[0].E01PAGDED.value));
     payval = payval + parseFloat(formatFloat(document.forms[0].E01PAGIVA.value));
     payval = payval + parseFloat(formatFloat(document.forms[0].E01PAGOTH.value));
     document.forms[0].E01PAGTOT.value = formatCCY("" + payval);
     if (trim(document.forms[0].E01CAPINT.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01CAPINT.value));
     capval = capval + tmp;
     if (trim(document.forms[0].E01CAPMOR.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01CAPMOR.value));
     capval = capval + tmp;
     document.forms[0].E01CAPTOT.value = formatCCY("" + capval);
     if (trim(document.forms[0].E01ADJINT.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01ADJINT.value));
     adjval = adjval + tmp;
     if (trim(document.forms[0].E01ADJMOR.value) == "") tmp = 0.00; else tmp = parseFloat(formatFloat(document.forms[0].E01ADJMOR.value));
     adjval = adjval + tmp;
     document.forms[0].E01ADJTOT.value = formatCCY("" + adjval);
 }
 
 function Recalculate() {
    UpdateField('E01BEFPRI','E01PAGPRI','','','E01AFTPRI');
    <% if(lnRenew.getH01FLGWK3().equals("R")){%>
    UpdateField('E01BEFREA','E01PAGREA','E01CAPREA','E01ADJREA','E01AFTREA');
    <% } %>
    UpdateField('E01BEFINT','E01PAGINT','E01CAPINT','E01ADJINT','E01AFTINT');
    UpdateField('E01BEFMOR','E01PAGMOR','E01CAPMOR','E01ADJMOR','E01AFTMOR');
    UpdateField('E01BEFIMP','E01PAGIMP','','','E01AFTIMP');
    UpdateField('E01BEFCOM','E01PAGCOM','','','E01AFTCOM');
    UpdateField('E01BEFDED','E01PAGDED','','','E01AFTDED');
    UpdateField('E01BEFIVA','E01PAGIVA','','','E01AFTIVA');
    UpdateField('E01BEFOTH','E01PAGOTH','','','E01AFTOTH');
    UpdateTotals();
    UpdateField('E01BEFTOT','E01PAGTOT','E01CAPTOT','E01ADJTOT','E01AFTTOT');
 }
   
 function UpdateAmts(typ,val) {
   if (typ == "1") document.forms[0].E01PAGIMP.value = val;
   else if (typ == "2") document.forms[0].E01PAGCOM.value = val;
   else if (typ == "3") document.forms[0].E01PAGDED.value = val;    
   else if (typ == "4") document.forms[0].E01PAGIVA.value = val;
   else if (typ == "5") document.forms[0].E01PAGOTH.value = val;
   Recalculate(); 
 }
 
 function verifyNum(elem) {
   	if (trim(elem.value)=="") elem.value="0.00";
	Recalculate(); 
  }
  
 function UpdateFlag(val) {
  if(val == true){
    document.forms[0].H01FLGWK1.value = "X";
  }
  if (document.forms[0].H01FLGWK1.value == "X"){
    document.forms[0].RECALC.checked=true;
  } else {
    document.forms[0].RECALC.checked=false;
  }
 }
 
</SCRIPT>
</head>
<body nowrap>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 

<H3 align="center">Prorroga de Pr&eacute;stamos <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_renewal.jsp, EDL0910"> 
</H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0910">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="E01DEABNK" VALUE="<%= lnRenew.getE01DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK1" VALUE="<%= lnRenew.getH01FLGWK1().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E01CNTINT" VALUE="<%= lnRenew.getE01CNTINT().trim()%>">
  <% 
   String field="";
   int numField=25;
   for(int i=1;i <= numField;i++){
     field = (i < 10) ? "0"+ i : ""+ i;
     try {
      if (!(lnRenew.getField("E01PTYP"+field).getString().trim().equals(""))) {
   %>
     <INPUT TYPE=HIDDEN ID="<%= lnRenew.getField("E01PTYP"+field).getString().trim()%>" NAME="E01PAMT<%= field%>" VALUE="<%= lnRenew.getField("E01PAMT"+field).getString().trim()%>">
   <% } 
     }
     catch (Exception e) {
     } 
   }
  %>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%" > 
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" > 
              <div align="left">
                <input type="text" size="10" maxlength="9" name="E01DEACUN" value="<%= lnRenew.getE01DEACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" size="45" maxlength="45" name="E01CUSNA1" value="<%= lnRenew.getE01CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" size="13" maxlength="12" name="E01DEAACC" value="<%= lnRenew.getE01DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" name="E01DEACCY" size="4" maxlength="3" value="<%= lnRenew.getE01DEACCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" size="5" maxlength="4" name="E01DEAPRO" value="<%= lnRenew.getE01DEAPRO().trim()%>" readonly>
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
 <h4>Información Básica</h4>
 <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Vencimiento Actual :</div>
            </td>
            <td nowrap> 
              	<input type="text" name="E01DEAMD1" size="2" maxlength="2" value="<%= lnRenew.getE01DEAMD1().trim()%>" readonly> 
            	<input type="text" name="E01DEAMD2" size="2" maxlength="2" value="<%= lnRenew.getE01DEAMD2().trim()%>" readonly> 
            	<input type="text" name="E01DEAMD3" size="2" maxlength="2" value="<%= lnRenew.getE01DEAMD3().trim()%>" readonly> 
            </td>
            <td nowrap> 
              <div align="right">Ulti./Calc. :</div>
            </td>
            <td nowrap>              
              <input type="text" name="E01DEALC1" size="2" maxlength="2" value="<%= lnRenew.getE01DEALC1().trim()%>" readonly> 
              <input type="text" name="E01DEALC2" size="2" maxlength="2" value="<%= lnRenew.getE01DEALC2().trim()%>" readonly> 
              <input type="text" name="E01DEALC3" size="2" maxlength="2" value="<%= lnRenew.getE01DEALC3().trim()%>" readonly>              
            </td>
            <td nowrap> 
              <div align="right">Modalidad :</div>
            </td>
            <td nowrap> 
              <input type="text" name="E01DEAICT" size="2" maxlength="1" value="<%= lnRenew.getE01DEAICT().trim()%>" readonly>              
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Abono Principal :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" id="txtright" name="E01ABOPRI" size="16" maxlength="16" value="<%= lnRenew.getE01ABOPRI().trim()%>" onkeypress="enterDecimal()"> 
            </td>
            <td nowrap> 
              <div align="right">Tasa Flotante :</div>
            </td>
            <td nowrap align="right" colspan=2> 
              <input type="text" name="E01DEAFTB" size="3" maxlength="2" value="<%= lnRenew.getE01DEAFTB().trim()%>" readonly> 
              <input type="text" name="E01DEAFTY" size="3" maxlength="2" value="<%= lnRenew.getE01DEAFTY().trim()%>" readonly>      
              <input type="text" id="txtright" name="E01DEAFRT" size="11" maxlength="10" value="<%= lnRenew.getE01DEAFRT().trim()%>" readonly>      
            </td>
          </tr>
		 
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap colspan=2>
              <INPUT type="text" size="2" maxlength="2" name="E01PAGVD1" value="<%= lnRenew.getE01PAGVD1().trim()%>" onkeypress="enterInteger()">
              <INPUT type="text" size="2" maxlength="2" name="E01PAGVD2" value="<%= lnRenew.getE01PAGVD2().trim()%>" onkeypress="enterInteger()">
              <INPUT type="text" size="2" maxlength="2" name="E01PAGVD3" value="<%= lnRenew.getE01PAGVD3().trim()%>" onkeypress="enterInteger()">
            </td>
            <td nowrap> 
              <div align="right">Tasa Spread :</div>
            </td>
            <td nowrap align="right" colspan=2> 
              <input type="text" id="txtright" name="E01DEARTE" size="11" maxlength="10" value="<%= lnRenew.getE01DEARTE().trim()%>" readonly>      
            </td>
          </tr>
          
		 <tr id="trclear">   
           <td nowrap> 
              <div align="right">Nuevo Vencimiento :</div>
            </td>
            <td nowrap colspan=2> 
              	<input type="text" name="E01NEWMD1" size="2" maxlength="2" value="<%= lnRenew.getE01NEWMD1().trim()%>" onkeypress="enterInteger()"> 
            	<input type="text" name="E01NEWMD2" size="2" maxlength="2" value="<%= lnRenew.getE01NEWMD2().trim()%>" onkeypress="enterInteger()"> 
            	<input type="text" name="E01NEWMD3" size="2" maxlength="2" value="<%= lnRenew.getE01NEWMD3().trim()%>" onkeypress="enterInteger()"> 
            </td>
            <td nowrap> 
              <div align="right">Tasa Inter&eacute;s :</div>
            </td>
            <td nowrap align="right" colspan=2> 
             <input type="text" id="txtright" name="E01RTELNS" size="11" maxlength="10" value="<%= lnRenew.getE01RTELNS().trim()%>" readonly>      
            </td>
          </tr>
		<tr id="trdark">			
		    <td nowrap> 
              <div align="right">Nueva Tasa Interes :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" id="txtright" name="E01NEWRTE" size="11" maxlength="11" value="<%= lnRenew.getE01NEWRTE().trim()%>" onkeypress="enterRate()"> 
            </td>
            <td nowrap> 
               <div align="right">Tipo Prorroga :</div>
            </td>
           <td nowrap colspan=2> 
              <select name="E01RNVTYP" onchange="UpdateFlag(true)" >
                 <OPTION value=" " <% if (!(lnRenew.getE01RNVTYP().equals("1") ||lnRenew.getE01RNVTYP().equals("2")||lnRenew.getE01RNVTYP().equals("3"))) out.print("selected"); %>></OPTION>
                 <OPTION value="1" <% if(lnRenew.getE01RNVTYP().equals("1")) out.print("selected");%>>Renovar Capital</OPTION>
                 <OPTION value="2" <% if(lnRenew.getE01RNVTYP().equals("2")) out.print("selected");%>>Renovar Capital & Intereses</OPTION>
                 <OPTION value="3" <% if(lnRenew.getE01RNVTYP().equals("3")) out.print("selected");%>>Cambiar Fecha Vencimiento</OPTION>
              </select>
            </td>
          </tr>
		<tr id="trclear">
			<td nowrap> 
              <div align="right">Ciclo Inter&eacute;s :</div>
            </td>
            <td nowrap colspan=2>               
             <input type="text" name="E01DEAIPD" size="3" maxlength="3" value="<%= lnRenew.getE01DEAIPD().trim()%>">              
            </td>
            <td nowrap> 
              <div align="right">Pr&oacute;x. Pago :</div>
            </td>
            <td nowrap colspan=2>              
              <input type="text" name="E01NEWRD1" size="2" maxlength="2" value="<%= lnRenew.getE01NEWRD1().trim()%>" onkeypress="enterInteger()"> 
              <input type="text" name="E01NEWRD2" size="2" maxlength="2" value="<%= lnRenew.getE01NEWRD2().trim()%>" onkeypress="enterInteger()"> 
              <input type="text" name="E01NEWRD3" size="2" maxlength="2" value="<%= lnRenew.getE01NEWRD3().trim()%>" onkeypress="enterInteger()">              
            </td>
            
          </tr>
		<tr id="trdark">
		    <td nowrap> 
              <div align="right">Ciclo Principal :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DEAPPD" size="3" maxlength="3" value="<%= lnRenew.getE01DEAPPD().trim()%>">                          
            </td>
            <td nowrap> 
              <div align="right">Pr&oacute;x. Pago :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01NEWHD1" size="2" maxlength="2" value="<%= lnRenew.getE01NEWHD1().trim()%>" onkeypress="enterInteger()"> 
              <input type="text" name="E01NEWHD2" size="2" maxlength="2" value="<%= lnRenew.getE01NEWHD2().trim()%>" onkeypress="enterInteger()"> 
              <input type="text" name="E01NEWHD3" size="2" maxlength="2" value="<%= lnRenew.getE01NEWHD3().trim()%>" onkeypress="enterInteger()">              
            </td>
            
          </tr>
		<tr id="trclear">
			<td nowrap> 
              <div align="right">Valor Cuota :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="text" name="E01DEAROA" size="16" maxlength="16" value="<%= lnRenew.getE01DEAROA().trim()%>" onkeypress="enterDecimal()"> 
            </td>
            <td nowrap> 
              <div align="right">Incluye Intereses :</div>
            </td>
            <td nowrap colspan=2> 
              <input type="radio" name="E01DEAIIP" value="Y" <% if(lnRenew.getE01DEAIIP().trim().equals("Y")) out.print("checked"); %>>Sí                          
              <input type="radio" name="E01DEAIIP" value="N" <% if(!(lnRenew.getE01DEAIIP().trim().equals("Y"))) out.print("checked"); %>>No                                      
            </td>
            
          </tr>
		<tr id="trdark">
		  <td nowrap> 
              <div align="right">Cuenta de Pago :</div>
          </td>
          <td nowrap colspan=5> 
          <table class="tableinfo" style="filter:''" >
		  <tr id="trdark">
		    <td nowrap> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap> 
              <div align="center">Banco</div>
            </td>
            <td nowrap> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap> 
              <div align="center">Moneda</div>
            </td>        
            <td nowrap> 
              <div align="center">Referencia</div>
            </td>
          </tr>
		  <tr id="trclear">
		    <td nowrap> 
              <div align="center"> 
                <input type=text name="E01PAGOPC" value="<%= lnRenew.getE01PAGOPC().trim()%>" size="3" maxlength="2">
                <input type=HIDDEN name="E01PAGOGL" value="<%= lnRenew.getE01PAGOGL().trim()%>">
                <input type="text" name="E01PAGCON" size="26" maxlength="25" readonly value="<%= lnRenew.getE01PAGCON().trim()%>"
                 		oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01DEABNK.value,'','E01PAGOGL','E01PAGOPC','10')">
			  </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01PAGOBK" size="3" maxlength="2" value="<%= lnRenew.getE01PAGOBK().trim()%>">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01PAGOBR" size="4" maxlength="3" value="<%= lnRenew.getE01PAGOBR().trim()%>"
                    oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01DEABNK.value,'','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01PAGOCY" size="4" maxlength="3" value="<%= lnRenew.getE01PAGOCY().trim()%>"
                 oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01DEABNK.value,'','','','')">
              </div>
            </td>
            <td nowrap> 
              <div align="center"> 
                <input type="text" name="E01PAGOAC" size="13" maxlength="12" value="<%= lnRenew.getE01PAGOAC().trim()%>"
                 oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01DEABNK.value,'','','','RT')">
              </div>
            </td>
          </tr>
          </table>
          </td>
        </tr>
		<tr id="trclear">
  			<td nowrap> 
              <div align="right">Descripcion :</div>
            </td>
            <td nowrap colspan=5> 
              <INPUT type="text" size="30" maxlength="30" name="E01DEANR1" value="<%= lnRenew.getE01DEANR1().trim()%>"><br>
              <INPUT type="text" size="30" maxlength="30" name="E01DEANR2" value="<%= lnRenew.getE01DEANR2().trim()%>">
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
  <h4>Distribuci&oacute;n del Pago</h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="center"><b>Concepto</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Saldo Anterior</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>A Pagar</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Capitalizar</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Ajustes</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Nuevo Saldo</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap> 
              <div align="right">Principal :</div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFPRI" size="16" maxlength="16" value="<%= lnRenew.getE01BEFPRI().trim()%>" readonly >
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGPRI" size="16" maxlength="16" value="<%= lnRenew.getE01PAGPRI().trim()%>" onkeypress="enterDecimal()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap COLSPAN=2> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTPRI" size="16" maxlength="16" value="<%= lnRenew.getE01AFTPRI().trim()%>" readonly>
            </td>
          </tr>
		 <%if(lnRenew.getH01FLGWK3().equals("R")){%>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Reajuste :</div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFREA" size="16" maxlength="16" value="<%= lnRenew.getE01BEFREA().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGREA" size="16" maxlength="16" value="<%= lnRenew.getE01PAGREA().trim()%>" onkeypress="enterDecimal()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01CAPREA" size="16" maxlength="16" value="<%= lnRenew.getE01CAPREA().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01ADJREA" size="16" maxlength="16" value="<%= lnRenew.getE01ADJREA().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this);">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTREA" size="16" maxlength="16" value="<%= lnRenew.getE01AFTREA().trim()%>" readonly>
            </td>
          </tr>
          <%}%>
			 <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">   
           <td nowrap > 
              <div align="right">Intereses :</div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFINT" size="16" maxlength="16" value="<%= lnRenew.getE01BEFINT().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGINT" size="16" maxlength="16" value="<%= lnRenew.getE01PAGINT().trim()%>" onkeypress="enterDecimal()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01CAPINT" size="16" maxlength="16" value="<%= lnRenew.getE01CAPINT().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01ADJINT" size="16" maxlength="16" value="<%= lnRenew.getE01ADJINT().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this);">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTINT" size="16" maxlength="16" value="<%= lnRenew.getE01AFTINT().trim()%>" readonly>
            </td>
          </tr>
			 <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap> 
              <div align="right">Mora :</div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFMOR" size="16" maxlength="16" value="<%= lnRenew.getE01BEFMOR().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGMOR" size="16" maxlength="16" value="<%= lnRenew.getE01PAGMOR().trim()%>" onkeypress="enterDecimal()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01CAPMOR" size="16" maxlength="16" value="<%= lnRenew.getE01CAPMOR().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01ADJMOR" size="16" maxlength="16" value="<%= lnRenew.getE01ADJMOR().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this);">
            </td>
            <td nowrap> 
              <input type="text" ID=TXTLABEL id="txtright" name="E01AFTMOR" size="16" maxlength="16" value="<%= lnRenew.getE01AFTMOR().trim()%>" readonly>
            </td>
          </tr>
			 <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">              
			 <td nowrap> 
              <div align="right"><a href="javascript:showDetail('1')"><b>Impuesto :</b></a></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFIMP" size="16" maxlength="16" value="<%= lnRenew.getE01BEFIMP().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGIMP" size="16" maxlength="16" value="<%= lnRenew.getE01PAGIMP().trim()%>" readonly>
            </td>
            <td nowrap COLSPAN=2> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTIMP" size="16" maxlength="16" value="<%= lnRenew.getE01AFTIMP().trim()%>" readonly>
            </td>
          </tr>
			 <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap> 
              <div align="right"><a href="javascript:showDetail('2')"><b>Comisiones :</b></a></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFCOM" size="16" maxlength="16" value="<%= lnRenew.getE01BEFCOM().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGCOM" size="16" maxlength="16" value="<%= lnRenew.getE01PAGCOM().trim()%>" readonly>
            </td>
            <td nowrap COLSPAN=2> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTCOM" size="16" maxlength="16" value="<%= lnRenew.getE01AFTCOM().trim()%>" readonly>
            </td>
          </tr>
			 <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">               
			 <td nowrap> 
              <div align="right"><a href="javascript:showDetail('3')"><b>Deducciones :</b></a></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFDED" size="16" maxlength="16" value="<%= lnRenew.getE01BEFDED().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGDED" size="16" maxlength="16" value="<%= lnRenew.getE01PAGDED().trim()%>" readonly>
            </td>
            <td nowrap COLSPAN=2> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTDED" size="16" maxlength="16" value="<%= lnRenew.getE01AFTDED().trim()%>" readonly>
            </td>
          </tr>
			 <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <%if (lnRenew.getE01CNTINT().trim().equals("07")) { %> 
			 <td nowrap> 
              <div align="right"><a href="javascript:showDetail('4')"><b>F.E.C.I :</b></a></div>
            </td>
			 <% } else { %> 
			 <td nowrap> 
              <div align="right"><a href="javascript:showDetail('4')"><b>I.V.A :</b></a></div>
            </td>
			 <% } %> 
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFIVA" size="16" maxlength="16" value="<%= lnRenew.getE01BEFIVA().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGIVA" size="16" maxlength="16" value="<%= lnRenew.getE01PAGIVA().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap COLSPAN=1> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01CAPIVA" size="16" maxlength="16" value="<%= lnRenew.getE01CAPIVA().trim()%>" onkeypress="enterSignCCY()" onChange="Recalculate()" onblur="verifyNum(this)">
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTIVA" size="16" maxlength="16" value="<%= lnRenew.getE01AFTIVA().trim()%>" readonly>
            </td>
          </tr>
          <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">               
			 <td nowrap> 
              <div align="right"><a href="javascript:showDetail('5')"><b>Otros Cargos :</b></a></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFOTH" size="16" maxlength="16" value="<%= lnRenew.getE01BEFOTH().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGOTH" size="16" maxlength="16" value="<%= lnRenew.getE01PAGOTH().trim()%>" readonly>
            </td>
            <td nowrap COLSPAN=2> 
              <div align="right"></div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTOTH" size="16" maxlength="16" value="<%= lnRenew.getE01AFTOTH().trim()%>" readonly>
            </td>
          </tr>
		  <tr id="<% if(lnRenew.getH01FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap> 
              <div align="right">Total :</div>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01BEFTOT" size="16" maxlength="16" value="<%= lnRenew.getE01BEFTOT().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01PAGTOT" size="16" maxlength="16" value="<%= lnRenew.getE01PAGTOT().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01CAPTOT" size="16" maxlength="16" value="<%= lnRenew.getE01CAPTOT().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01ADJTOT" size="16" maxlength="16" value="<%= lnRenew.getE01ADJTOT().trim()%>" readonly>
            </td>
            <td nowrap> 
              <input type="text" id="txtright" name="E01AFTTOT" size="16" maxlength="16" value="<%= lnRenew.getE01AFTTOT().trim()%>" readonly>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <br>
  <% if(userPO.getPurpose().equals("MAINTENANCE")){%>
  <table class=tbenter style="width:60%" align=center>
   <tr>
    <td align=center>
     <input type="checkbox" name="RECALC" value="" onClick="UpdateFlag(this.checked)"><b> Recalcular</b> 
 	</td>
 	<% if(error.getERWRNG().equals("Y")){%>
 	<td align=center width=50%>
 	 <input type="checkbox" name="H01FLGWK2" value="A" <% if(lnRenew.getH01FLGWK2().equals("A")){ out.print("checked");} %>><b>Aceptar con Errores</b>
     </td>
    <% } %>
    </tr>
  </table>
 <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
</p>
 <% } else { %>
 <SCRIPT Language="Javascript">
   var j=document.forms[0].elements.length;
    var felem=document.forms[0].elements;
    for(i=0;i< j;i++){
       if (felem[i].tagName!=="select"){
         if (felem[i].type=="text") {
           felem[i].readOnly=true;
           felem[i].oncontextmenu = "";
         } else felem[i].disabled=true;
       } 
       else { felem[i].disabled=true;}
    }
</SCRIPT> 
 <% } %>
 <SCRIPT LANGUAGE="JavaScript">
   Recalculate();
 </SCRIPT>
</form>
</body>
</html>
