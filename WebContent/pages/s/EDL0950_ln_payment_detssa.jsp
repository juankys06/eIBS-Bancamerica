<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<%@ page import = "datapro.eibs.master.Util" %>
<html>
<head>
<title>Pago de Pr&eacute;stamos</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="/eIBS_R04M07/pages/style.css" rel="stylesheet">

<SCRIPT SRC="/eIBS_R04M07/pages/s/javascripts/eIBS.js"> </SCRIPT>
<SCRIPT SRC="/eIBS_R04M07/pages/s/javascripts/optMenu.js"> </SCRIPT>
</head>

<jsp:useBean id= "lnCancelDet" class= "datapro.eibs.beans.EDL095002Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%> 
<SCRIPT LANGUAGE="JavaScript">
builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

function showCalculator(){
	pg= prefix +language + "EDL0950_ln_payment_calc.jsp";	
	CenterNamedWindow(pg,'Information',300,280,1);           
}

function showLateChgCalc(){
	pg= prefix +language + "EDL0135_ln_latechg_calc.jsp";	
	CenterNamedWindow(pg,'Information',400,450,1);           
}

function showCupon(){
	pg= prefix +language + "EDL0950_ln_payment_print.jsp";
	CenterNamedWindow(pg,'Cupon',500,500,1);           
}

function updateLateChg(val){
   document.forms[0]["E02PAGMOR"].value = formatCCY(val);
}

function closeWin(){
     window.location.href = "/eIBS_R04M07/pages/background.htm";
 }

function showSimulation(){
	pg= webapp + "/servlet/datapro.eibs.products.JSEDL0950?SCREEN=28";	
	CenterNamedWindow(pg,'Information',500,500,1);           
}

function disableSubmit(){
	document.forms[0].EIBSBTN.disabled = true;
	document.forms[0].submit();
}
</SCRIPT>
<% String title="";
   if (userPO.getOption().equals("LC")) title = title="Pago L&iacute;nea de Cr&eacute;dito";
   else if (userPO.getOption().equals("SP")) title = title="Pago Sobregiro Pactado";
   else title="Pago de Cr&eacute;ditos";
%>
<H3 align="center"><%= title %><img src="/eIBS_R04M07/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="ln_payment_det.jsp, EDL0950"> 
</H3>
<hr size="4">
<form method="post" action="/eIBS_R04M07/servlet/datapro.eibs.products.JSEXEDL0950" onsubmit="disableSubmit()">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="22">
  <INPUT TYPE=HIDDEN NAME="E02DEABNK" VALUE="<%= lnCancelDet.getE02DEABNK().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02PREAMT" VALUE="<%= lnCancelDet.getE02PREAMT().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02PREBSE" VALUE="<%= lnCancelDet.getE02PREBSE().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02PREDYS" VALUE="<%= lnCancelDet.getE02PREDYS().trim()%>">
  <INPUT TYPE=HIDDEN NAME="E02PRERTE" VALUE="<%= lnCancelDet.getE02PRERTE().trim()%>">
  
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
                <input type="text" size="9" maxlength="9" name="E02DEACUN" value="<%= lnCancelDet.getE02DEACUN().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%" > 
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" > 
              <div align="left">
                <input type="text" size="45" maxlength="45" name="E02CUSNA1" value="<%= lnCancelDet.getE02CUSNA1().trim()%>" readonly>
              </div>
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" size="12" maxlength="12" name="E02DEAACC" value="<%= lnCancelDet.getE02DEAACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"> 
                <input type="text" name="E02DEACCY" size="3" maxlength="3" value="<%= lnCancelDet.getE02DEACCY().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left">
                <input type="text" size="4" maxlength="4" name="E02DEAPRO" value="<%= lnCancelDet.getE02DEAPRO().trim()%>" readonly>
              </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>Saldos</h4>
 <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="36%"> 
              <div align="right"><b>Concepto</b></div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><b>Saldo Anterior</b></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><b>Transacci&oacute;n</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><b>Nuevo Saldo</b></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="36%"> 
              <div align="right">Principal :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFPRI().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNPRI().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTPRI().trim())%></div>
            </td>
          </tr>
		 <%if(lnCancelDet.getH02FLGWK3().equals("R")){%>
          <tr id="trdark"> 
            <td nowrap width="36%"> 
              <div align="right">Reajuste :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFREA().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNREA().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTREA().trim())%></div>
            </td>
          </tr>
          <%}%>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">   
           <td nowrap width="36%"> 
              <div align="right">Intereses :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFINT().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNINT().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTINT().trim())%></div>
            </td>
          </tr>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap width="36%"> 
              <div align="right">Interes por Mora :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFMOR().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNMOR().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTMOR().trim())%></div>
            </td>
          </tr>
          <%if(lnCancelDet.getH02FLGWK3().equals("R")){%>
          <tr id="trclear"> 
            <td nowrap width="36%"> 
              <div align="right">Reajuste por Mora:</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFREX().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNREX().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTREX().trim())%></div>
            </td>
          </tr>
          <%}%>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap width="36%"> 
              <div align="right">Impuesto :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFIMP().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNIMP().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTIMP().trim())%></div>
            </td>
          </tr>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">               
			 <td nowrap width="36%"> 
              <div align="right">Comisiones :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFCOM().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNCOM().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTCOM().trim())%></div>
            </td>
          </tr>
			 <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap width="36%"> 
              <div align="right">Deducciones :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFDED().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNDED().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTDED().trim())%></div>
            </td>
          </tr>
			 <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">               
			 <td nowrap width="36%"> 
              <div align="right">I.V.A. :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFIVA().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNIVA().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTIVA().trim())%></div>
            </td>
          </tr>
			 <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap width="36%"> 
              <div align="right">Total :</div>
            </td>
            <td nowrap width="21%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02BEFTOT().trim())%></div>
            </td>
            <td nowrap width="23%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02TRNTOT().trim())%></div>
            </td>
            <td nowrap width="20%"> 
              <div align="right"><%= Util.fcolorCCY(lnCancelDet.getE02AFTTOT().trim())%></div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <h4>
  Prioridad de Pagos </h4>
  <table class="tableinfo">
    <tr > 
      <td nowrap > 
        <table cellspacing=0 cellpadding=2 width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="16%"  > 
              <div align="center"><%= lnCancelDet.getE02DEAPP1().trim()%></div>
            </td>
            <td nowrap width="14%"  > 
              <div align="center"><%= lnCancelDet.getE02DEAPP2().trim()%></div>
            </td>
            <td nowrap width="16%" > 
              <div align="center"><%= lnCancelDet.getE02DEAPP3().trim()%></div>
            </td>
            <td nowrap width="22%"  > 
              <div align="center"><%= lnCancelDet.getE02DEAPP4().trim()%></div>
            </td>
            <td nowrap width="17%" > 
              <div align="center"><%= lnCancelDet.getE02DEAPP5().trim()%></div>
            </td>
            <td nowrap width="15%"  > 
              <div align="center"><%= lnCancelDet.getE02DEAPP6().trim()%></div>
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="16%" > 
              <div align="center">Principal</div>
            </td>
            <td nowrap width="14%" > 
              <div align="center">Intereses</div>
            </td>
            <td nowrap width="16%" > 
              <div align="center">Mora</div>
            </td>
            <td nowrap width="22%" > 
              <div align="center">Impuestos / Comisiones</div>
            </td>
            <td nowrap width="17%" > 
              <div align="center">Deducciones</div>
            </td>
            <td nowrap width="15%" > 
              <div align="center">I.V.A.</div>
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
            <td nowrap colspan="2"> 
              <div align="right">PRINCIPAL :</div>
            </td>
            <td nowrap colspan="3">
              
              <input type="text" size="15" maxlength="15" name="E02PAGPRI" value="<%= lnCancelDet.getE02PAGPRI().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
             
            </td>
          </tr>	
	     <%if(lnCancelDet.getH02FLGWK3().equals("R")){%>
          <tr id="trclear"> 
            <td nowrap colspan="2"> 
              <div align="right">REAJUSTE :</div>
            </td>
            <td nowrap colspan="3"> 
             <input type="text" size="15" maxlength="15" name="E02PAGREA" value="<%= lnCancelDet.getE02PAGREA().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
            </td>
          </tr>
		  <%}%>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trdark"); else out.print("trclear"); %>">               
		  <td nowrap colspan="2"> 
              <div align="right">INTERESES :</div>
            </td>
            <td nowrap colspan="3"> 
              
              <input type="text" size="15" maxlength="15" name="E02PAGINT" value="<%= lnCancelDet.getE02PAGINT().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
              
            </td>
          </tr>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R")) out.print("trclear"); else out.print("trdark"); %>">
		   <td nowrap colspan="2">
		      <% if (userPO.getType().equals("1")) {%> 
              <div align="right"><A HREF="javascript:showLateChgCalc()">INTERES POR MORA :</A></div>
              <% } else out.print("<div align=\"right\">INTERES POR MORA :</div>");%>
            </td>
            <td nowrap colspan="3">
              <input type="text" size="15" maxlength="15" name="E02PAGMOR" value="<%= lnCancelDet.getE02PAGMOR().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
            </td>
          </tr>
          <%if(lnCancelDet.getH02FLGWK3().equals("R")){%>
          <tr id="trdark"> 
            <td nowrap colspan="2"> 
              <div align="right">REAJUSTE POR MORA :</div>
            </td>
            <td nowrap colspan="3">
              <input type="text" size="15" maxlength="15" name="E02PAGREX" value="<%= lnCancelDet.getE02PAGREX().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>             
            </td>
          </tr>
		  <%}%>
		  <% if(lnCancelDet.getE02PAGFLG().equals("TP") || lnCancelDet.getE02PAGFLG().equals("TO") || !lnCancelDet.getE02PAGABO().trim().equals("0.00")) {%>
		  <tr id="trclear">
		   <td nowrap colspan="2"> 
              <div align="right">CAPITAL PREPAGADO :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" size="15" maxlength="15" name="E02PAGABO" value="<%= lnCancelDet.getE02PAGABO().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
            </td>
          </tr>
          <%}%>
          <% if((lnCancelDet.getE02PAGFLG().equals("TP") || lnCancelDet.getE02PAGFLG().equals("TO") || !lnCancelDet.getE02PAGABD().trim().equals("0.00")) && lnCancelDet.getH02FLGWK3().equals("R")) {%>
		  <tr id="trdark">
		   <td nowrap colspan="2"> 
              <div align="right">REAJUSTE PREPAGADO :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" size="15" maxlength="15" name="E02PAGABD" value="<%= lnCancelDet.getE02PAGABD().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
            </td>
          </tr>
          <%}%>
          <%
           String name="";
           boolean par=true;
           for (int i=1;i<26;i++) {
            if (i<10) name="0"+i; else name=""+i;            
		    if(!lnCancelDet.getField("E02PDSC"+name).getString().trim().equals("")){
		     if (par == true) par=false; else par=true;
		  %> 
			<tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R") || par==false) out.print("trclear"); else out.print("trdark"); %>">
			 <td nowrap colspan="2"> 
              <div align="right">
              <% if (i==25 && userPO.getType().equals("1")) {
                 out.print("<A HREF=\"javascript:showCalculator()\">" + lnCancelDet.getField("E02PDSC"+name).getString().trim() + "</A>");
                } else {
                 out.print(lnCancelDet.getField("E02PDSC"+name).getString().trim());
                } %> : </div>
             </td>
             <td nowrap colspan="3"> 
              <input type="text" size="15" maxlength="15" name="E02PAMT<%= name%>" value="<%= lnCancelDet.getField("E02PAMT"+name).getString().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
             </td>
          </tr>
          <%
		   	}
		   }
		  %> 
		  
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R") || par==true) out.print("trdark"); else out.print("trclear"); %>">               
		  	<td nowrap colspan="2"> 
              <div align="right">TOTAL :</div>
            </td>
            <td nowrap> 
              <input type="text" size="15" maxlength="15" name="E02PAGTOT" value="<%= lnCancelDet.getE02PAGTOT().trim()%>" <% if (userPO.getType().equals("1") || lnCancelDet.getE02PAGFLG().equals("DS")) out.print("onKeypress=\"enterDecimal()\""); else out.print("readonly");%>>
            </td>
            <td nowrap colspan="2" align="center">
             <% if (userPO.getType().equals("2") && userPO.getRedirect().equals("") ) {%>
              <input type="button" name="SIMUL" value="Simular" onclick="showSimulation()">
             <% }%>
            </td>
          </tr>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R") || par==true) out.print("trclear"); else out.print("trdark"); %>">               
			 <td nowrap colspan="2">&nbsp;</td>
            <td nowrap colspan="3">&nbsp;</td>
          </tr>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R") || par==true) out.print("trdark"); else out.print("trclear"); %>">               
			 <td nowrap colspan="2"> 
              <div align="right">Descripci&oacute;n :</div>
            </td>
            <td nowrap width="21%"> 
              <input type="text" size="30" maxlength="30" name="E02DEANR1" 
                value="<% if (lnCancelDet.getE02DEANR1().trim().equals("")) out.print(title); else out.print(lnCancelDet.getE02DEANR1().trim());%>">
            </td>
            <td nowrap width="18%"> 
              <div align="right">Fecha Valor :</div>
            </td>
            <td nowrap width="20%"> 
              <input type="text" size="2" maxlength="2" name="E02PAGVD1" value="<%= lnCancelDet.getE02PAGVD1().trim()%>" <% if (userPO.getType().equals("1") || userPO.getType().equals("1")) out.print("onKeypress=\"enterInteger()\""); else out.print("readonly");%>>
              <input type="text" size="2" maxlength="2" name="E02PAGVD2" value="<%= lnCancelDet.getE02PAGVD2().trim()%>" <% if (userPO.getType().equals("1")) out.print("onKeypress=\"enterInteger()\""); else out.print("readonly");%>>
              <input type="text" size="2" maxlength="2" name="E02PAGVD3" value="<%= lnCancelDet.getE02PAGVD3().trim()%>" <% if (userPO.getType().equals("1")) out.print("onKeypress=\"enterInteger()\""); else out.print("readonly");%>>
            </td>
          </tr>
		  <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R") || par==true) out.print("trclear"); else out.print("trdark"); %>">               
			 <td nowrap colspan="2">&nbsp;</td>
            <td nowrap width="21%"> 
              <input type="text" size="30" maxlength="30" name="E02DEANR2" 
                value="<% if (lnCancelDet.getE02DEANR2().trim().equals("")) out.print(lnCancelDet.getE02DEAACC()); else out.print(lnCancelDet.getE02DEANR2().trim());%>">
            </td>
            <td nowrap width="18%">&nbsp;</td>
            <td nowrap width="20%">&nbsp;</td>
          </tr>
			 <tr id="<% if(lnCancelDet.getH02FLGWK3().equals("R") || par==true) out.print("trdark"); else out.print("trclear"); %>">               
			 <td align=center colspan="5"><b>Cuenta de Pago </b></td>
          </tr>
         </table> 
         <table class="tableinfo" style="filter:''" >
			 <tr id="trdark">               
			 <td nowrap width="31%"> 
              <div align="center">Concepto</div>
            </td>
            <td nowrap width="10%"> 
              <div align="center">Banco</div>
            </td>
            <td nowrap width="21%"> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap width="18%"> 
              <div align="center">Moneda</div>
            </td>
            <td nowrap width="20%"> 
              <div align="center">Referencia</div>
            </td>
            <td> 
              <div align="center">Titular</div>
            </td>
          </tr>
			 <tr id="trclear">               
			 <td nowrap width="31%"> 
              <div align="center"> 
                <input type=text name="E02PAGOPC" value="<%= lnCancelDet.getE02PAGOPC().trim()%>" size="2" maxlength="2" readonly>
                <input type=HIDDEN name="E02PAGOGL" value="<%= lnCancelDet.getE02PAGOGL().trim()%>">
                <input type=HIDDEN name="SETHLP" value="">  
                <input type="text" name="E02PAGCON" size="25" maxlength="25" readonly value="<%= lnCancelDet.getE02PAGCON().trim()%>"
                 		oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02DEABNK.value,'SETHLP','E02PAGOGL','E02PAGOPC','10')">
			  </div>
            </td>
            <td nowrap width="10%"> 
              <div align="center"> 
                <input type="text" name="E02PAGOBK" size="2" maxlength="2" value="<%= lnCancelDet.getE02PAGOBK().trim()%>">
              </div>
            </td>
            <td nowrap width="21%"> 
              <div align="center"> 
                <input type="text" name="E02PAGOBR" size="3" maxlength="3" value="<%= lnCancelDet.getE02PAGOBR().trim()%>"
                    oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02DEABNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="18%"> 
              <div align="center"> 
                <input type="text" name="E02PAGOCY" size="3" maxlength="3" value="<%= lnCancelDet.getE02PAGOCY().trim()%>"
                 oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02DEABNK.value,'','','','')">
              </div>
            </td>
            <td nowrap width="20%"> 
              <div align="center"> 
                <input type="text" name="E02PAGOAC" size="16" maxlength="16" value="<%= lnCancelDet.getE02PAGOAC().trim()%>"
                 oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02DEABNK.value,'',document.forms[0].E02DEACUN.value,'',document.forms[0].SETHLP.value); return false;">
              </div>
            </td>
              
			<td nowrap width="21%" > 
              <div align="center"> 
                <input type="text" readonly name="E02PAGNA1" size="25" maxlength="25" value="<%= lnCancelDet.getE02PAGNA1().trim()%>">
              </div>
            </td>
			            
          </tr>
        </table>
        
      </td>
    </tr>
  </table>
  <% if (userPO.getRedirect().equals("")) {%>
	  <p align="center"> 
	    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
	  </p>
  <% } else {%>
      <BR>
      <table cellspacing=0 cellpadding=2 width="100%" border="0">
  		<tr> 
	      <td align="center">  
   			 <input id="EIBSBTN" type=button name="imp" value="cupon" onclick="showCupon()">
  		  </td>
  		  <td align="center">  
   			 <input id="EIBSBTN" type=button name="cls" value="Cerrar" onclick="closeWin()">
  		  </td>
  		</tr>
      </table>
  <% } %>
</form>
</body>
</html>
