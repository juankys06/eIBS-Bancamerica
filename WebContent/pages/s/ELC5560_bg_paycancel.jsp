<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Boleta de Garantia</title>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "bolgaran" class= "datapro.eibs.beans.ELC556001Message"  scope="session" />
<jsp:useBean id= "bolaviben" class= "datapro.eibs.beans.ELC500004Message"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT SRC="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  <%
   String htitle ="";

   if (bolgaran.getE01TYPOPE().equals("1")) {
      htitle ="Pago al Beneficiario";
   } else {
      htitle ="Cancelacion";
   }
  %>

  builtHPopUp();
  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }

  function UpdateField(payfield,totfield,revfield){
   var totval;
   var payval;
   var revval=0.00;

    try{
     totval= parseFloat(replaceAll(document.forms[0][totfield].value,","));
     if (isNaN(totval)) totval=0.00;}
    catch(e){
     totval=0.00;
    }
    try{
     payval=parseFloat(replaceAll(document.forms[0][payfield].value,","));}
    catch(e){
     payval=0.00;
    }
    revval=totval-payval;
    document.forms[0][revfield].value = formatCCY(""+revval);
  }

  function verifyNum(elem){
   if (trim(elem.value)=="") elem.value="0.00";
  }

  function UpdateTotal(){

   var total;
    try{
     total= parseFloat(replaceAll(document.forms[0].E01PAGPRI.value,","));}
    catch(e){
     total=0.00;
    }
    try{
     total=total+parseFloat(replaceAll(document.forms[0].E01COMAMN.value,","));}
    catch(e){
    }
    try{
     total=total+parseFloat(replaceAll(document.forms[0].E01PAGREV.value,","));}
    catch(e){
    }

   document.forms[0].E01PAGTOT.value=formatCCY(""+total);
  }

 function RecalculateComm(){
    UpdateField('E01PAGCOM','E01COMAMN','E01REVCOM');
    //UpdateTotal();
 }

  function RecalculateReaj(){
    UpdateField('E01PAGREV','E01REVAMN','E01REVREV');
    UpdateTotal();
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


<h3 align="center"><%=htitle%> - Boleta de Garantia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bg_paycancel.jsp,ELC5560"></h3>
<hr size="4">

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.bolgaran.JSELC5560">

  <input type="hidden" name="SCREEN" value="3">
  <input type="hidden" name="E01TYPOPE" value="<%=bolgaran.getE01TYPOPE()%>">
  <input type="hidden" name="E01LCMACD" value="<%=bolgaran.getE01LCMACD()%>">
  <input type="hidden" name="E01LCMBNK" value="<%=bolgaran.getE01LCMBNK()%>">
  <table class="tableinfo">
    <tr>
      <td nowrap>
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark">
            <td nowrap width="16%" >
              <div align="right"><b>Cliente :</b></div>
            </td>
            <td nowrap width="20%" >
              <div align="left">
                <input type="text" name="E01LCMCUN" size="9" maxlength="9" readonly value="<%= bolgaran.getE01LCMCUN().trim()%>">
            </td>
            <td nowrap width="16%" >
              <div align="right"><b>Nombre :</b> </div>
            </td>
            <td nowrap colspan="3" >
              <div align="left">
                <input type="text" name="E01CUSNA1" size="45" maxlength="45" readonly value="<%= bolgaran.getE01CUSNA1().trim()%>">
              </div>
            </td>
          </tr>
          <tr id="trdark">
            <td nowrap width="16%">
              <div align="right"><b>Cuenta :</b></div>
            </td>
            <td nowrap width="20%">
              <div align="left">
                <input type="text" name="E01LCMACC" size="12" maxlength="12" value="<%= bolgaran.getE01LCMACC().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%">
              <div align="right"><b>Moneda : </b></div>
            </td>
            <td nowrap width="16%">
              <div align="left"><b>
                <input type="text" name="E01LCMCCY" size="3" maxlength="3" value="<%= bolgaran.getE01LCMCCY().trim()%>" readonly>
                </b> </div>
            </td>
            <td nowrap width="16%">
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%">
              <div align="left"><b>
                <input type="text" name="E01LCMPRO" size="4" maxlength="4" readonly value="<%= bolgaran.getE01LCMPRO().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <H4>Informaci&oacute;n Relevante</H4>
  <table class=tableinfo>
  <tr>
   <td>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr id=trdark>
	      <td align=right nowrap>Fecha Apertura :</td>
	      <td>
	        <input size="3" type="text" name="E01LCMID1" maxlength="2" value="<%=bolgaran.getE01LCMID1()%>">
			<INPUT size="3" type="text" name="E01LCMID2" maxlength="2" value="<%=bolgaran.getE01LCMID2()%>">
			<INPUT size="3" type="text" name="E01LCMID3" maxlength="2" value="<%=bolgaran.getE01LCMID3()%>">
		  </td>
	      <td align=right nowrap>Fecha Vcto. :</td>
          <td>
            <input size="3" type="text" name="E01LCMEX1" maxlength="2" value="<%=bolgaran.getE01LCMEX1()%>">
            <INPUT size="3" type="text" name="E01LCMEX2" maxlength="2" value="<%=bolgaran.getE01LCMEX2()%>">
            <INPUT size="3" type="text" name="E01LCMEX3" maxlength="2" value="<%=bolgaran.getE01LCMEX3()%>">
          </td>
    </tr>
    <tr id=trclear>
      <td align=right nowrap>Monto Origen :</td>
      <td><INPUT type="text" name="E01LCMOAM" size="17" readonly value="<%=bolgaran.getE01LCMOAM()%>"> </td>
      <td align=right nowrap>Monto Comision :</td>
      <td><INPUT type="text" name="E01COMAMN" size="17" readonly value="<%=bolgaran.getE01COMAMN()%>"> </td>
    </tr>
    <tr id=trdark>
      <td align=right nowrap>Monto Reajuste :</td>
      <td ><INPUT type="text" name="E01REVAMN" readonly size="17" value="<%=bolgaran.getE01REVAMN()%>"></td>
      <td align=right nowrap>Fecha Aviso Pag. Ben. :</td>
      <td>
		<input readonly size="3" type="text" name="E04LCMCNM" maxlength="2" value="<%=bolaviben.getE04LCMCNM()%>">
		<INPUT readonly size="3" type="text" name="E04LCMCND" maxlength="2" value="<%=bolaviben.getE04LCMCND()%>">
		<INPUT readonly size="3" type="text" name="E04LCMCNY" maxlength="2" value="<%=bolaviben.getE04LCMCNY()%>">
	  </td>
    </tr>
   </table>
   </td>
  </tr>
  </table>

<% if (bolgaran.getE01TYPOPE().equals("2")) {%>
 <H4>Cancelacion</H4>
 <table class=tableinfo>
  <tr>
   <td>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
      <td align=right nowrap>Monto Boleta :</td>
      <td colspan=3>
      	<INPUT type="text" name="E01PAGPRI" size="15" maxlength="15" onkeypress="enterDecimal()" value="<%=bolgaran.getE01PAGPRI()%>" onChange="UpdateTotal()" onblur="verifyNum(this)">
      </td>
    </tr>
    <tr id=trclear>
      <td align=right nowrap>Monto Reajuste :</td>
      <td>
      	<INPUT type="text" name="E01PAGREV" size="15" maxlength="15" onkeypress="enterDecimal()" value="<%=bolgaran.getE01PAGREV()%>" onChange="RecalculateReaj()" onblur="verifyNum(this)">
      </td>
      <td align=right nowrap>Reversion Reajuste :</td>
      <td>
      	<INPUT type="text" name="E01REVREV" size="15" maxlength="15" value="<%=bolgaran.getE01REVREV()%>" readonly>
      </td>
    </tr>
    <tr id=trdark>
      <td align=right nowrap>Monto Comision :</td>
      <td>
      	<INPUT type="text" name="E01PAGCOM" onkeypress="enterDecimal()" maxlength="15" size="15" value="<%=bolgaran.getE01PAGCOM()%>" onChange="RecalculateComm()" onblur="verifyNum(this)">
      </td>
      <td align=right nowrap>Reversa Comision :</td>
      <td>
      	<INPUT type="text" name="E01REVCOM" size="15" maxlength="15" value="<%=bolgaran.getE01REVCOM()%>" readonly>
      </td>
    </tr>
    <tr id=trclear>
      <td align=right nowrap>Total a Pagar :</td>
      <td>
      	<INPUT type="text" name="E01PAGTOT" onkeypress="enterDecimal()" size="15" maxlength="15" value="<%=bolgaran.getE01PAGTOT()%>" readonly>
      </td>
      <td align=right nowrap>Fecha Valor :</td>
      <td>
            <input size="3" type="text" name="E01PAGVD1" maxlength="2" value="<%=bolgaran.getE01PAGVD1()%>" onkeypress="enterInteger()">
            <INPUT size="3" type="text" name="E01PAGVD2" maxlength="2" value="<%=bolgaran.getE01PAGVD2()%>" onkeypress="enterInteger()">
            <INPUT size="3" type="text" name="E01PAGVD3" maxlength="2" value="<%=bolgaran.getE01PAGVD3()%>" onkeypress="enterInteger()">
      </td>
    </tr>

    <tr id="trclear">
      <td nowrap colspan="4">
      <table id="headTable" class="tableinfo" style="filter:";>
   		  <tr id="trdark">
      		<td nowrap align="center" >Concepto</td>
      		<td nowrap align="center" >Banco </td>
      		<td nowrap align="center" >Sucursal</td>
      		<td nowrap align="center" >Moneda</td>
      		<td nowrap align="center" >Referencia</td>
    	  </tr>
          <tr id="trclear">
            <td nowrap >
              <div align="center" nowrap>
                <input type="text" name="E01PAGEPC" value="<%= bolgaran.getE01PAGEPC().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E01PAGEGL" value="<%= bolgaran.getE01PAGEGL().trim()%>">
                <input type="text" name="E01PAGECO" size="25" maxlength="25" readonly value="<%= bolgaran.getE01PAGECO().trim()%>"
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01LCMBNK.value,'','E01PAGEGL','E01PAGEPC',document.forms[0].E01LCMACD.value); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="left">
                <input type="text" name="E01PAGEBK" size="2" maxlength="2" value="<%= bolgaran.getE01PAGEBK().trim()%>">
              </div>
            </td>
            <td nowrap >
              <div align="left">
                <input type="text" name="E01PAGEBR" size="3" maxlength="3" value="<%= bolgaran.getE01PAGEBR().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01LCMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="center">
                <input type="text" name="E01PAGECY" size="3" maxlength="3" value="<%= bolgaran.getE01PAGECY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01LCMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="center">
                <input type="text" name="E01PAGEAC" size="12" maxlength="12"  value="<%= bolgaran.getE01PAGEAC().trim()%>"
                oncontextmenu="showPopUp(acctHlpByClient,this.name,document.forms[0].E01LCMBNK.value,'',document.forms[0].E01LCMCUN.value,'','RT'); return false;">
              </div>
            </td>
          </tr>
   	  </table>
      </td>
     </tr>
   </table>
   </td>
  </tr>
  </table>
 <% } else {%>
  <H4>Beneficiario</H4>

 <table class=tableinfo>
  <tr>
   <td>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
      <td align=right nowrap>Monto Boleta :</td>
      <td colspan=3>
      	<INPUT type="text" name="E01PAGPRI" size="15" maxlength="15" onkeypress="enterDecimal()" value="<%=bolgaran.getE01PAGPRI()%>" onChange="UpdateTotal()" onblur="verifyNum(this)">
      </td>
    </tr>
    <tr id=trclear>
      <td align=right nowrap>Monto Reajuste :</td>
      <td>
      	<INPUT type="text" name="E01PAGREV" size="15" maxlength="15" onkeypress="enterDecimal()" value="<%=bolgaran.getE01PAGREV()%>" onChange="RecalculateReaj()" onblur="verifyNum(this)">
      </td>
      <td align=right nowrap>Reversion Reajuste :</td>
      <td>
      	<INPUT type="text" name="E01REVREV" size="15" maxlength="15" value="<%=bolgaran.getE01REVREV()%>" readonly>
      </td>
    </tr>
    <tr id=trclear>
      <td align=right nowrap>Total a Pagar :</td>
      <td>
      	<INPUT type="text" name="E01PAGTOT" onkeypress="enterDecimal()" size="15" maxlength="15" value="<%=bolgaran.getE01PAGTOT()%>" readonly>
      </td>
      <td align=right nowrap>Fecha Valor :</td>
      <td>
            <input size="3" type="text" name="E01PAGVD1" maxlength="2" value="<%=bolgaran.getE01PAGVD1()%>" onkeypress="enterInteger()">
            <INPUT size="3" type="text" name="E01PAGVD2" maxlength="2" value="<%=bolgaran.getE01PAGVD2()%>" onkeypress="enterInteger()">
            <INPUT size="3" type="text" name="E01PAGVD3" maxlength="2" value="<%=bolgaran.getE01PAGVD3()%>" onkeypress="enterInteger()">
      </td>
    </tr>

    <tr id="trclear">
      <td nowrap colspan="4">
      <table id="headTable" class="tableinfo" style="filter:";>
   		  <tr id="trdark">
      		<td nowrap align="center" >Concepto</td>
      		<td nowrap align="center" >Banco </td>
      		<td nowrap align="center" >Sucursal</td>
      		<td nowrap align="center" >Moneda</td>
      		<td nowrap align="center" >Referencia</td>
    	  </tr>
          <tr id="trclear">
            <td nowrap >
              <div align="center" nowrap>
                <input type="text" name="E01PAGOPC" value="<%= bolgaran.getE01PAGOPC().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E01PAGOGL" value="<%= bolgaran.getE01PAGOGL().trim()%>">
                <input type="text" name="E01PAGCON" size="25" maxlength="25" readonly value="<%= bolgaran.getE01PAGCON().trim()%>"
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01LCMBNK.value,'','E01PAGOGL','E01PAGOPC',document.forms[0].E01LCMACD.value); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="left">
                <input type="text" name="E01PAGOBK" size="2" maxlength="2" value="<%= bolgaran.getE01PAGOBK().trim()%>">
              </div>
            </td>
            <td nowrap >
              <div align="left">
                <input type="text" name="E01PAGOBR" size="3" maxlength="3" value="<%= bolgaran.getE01PAGOBR().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01LCMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="center">
                <input type="text" name="E01PAGOCY" size="3" maxlength="3" value="<%= bolgaran.getE01PAGOCY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01LCMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="center">
                <input type="text" name="E01PAGOAC" size="12" maxlength="12"  value="<%= bolgaran.getE01PAGOAC().trim()%>"
                oncontextmenu="showPopUp(accountHelp,this.name,document.forms[0].E01LCMBNK.value,'','','','RT'); return false;">
              </div>
            </td>
          </tr>
   	  </table>
      </td>
     </tr>
   </table>
   </td>
  </tr>
  </table>

  <H4>Cargos</H4>

 <table class=tableinfo>
  <tr>
   <td>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr id=trdark>
      <td align=right nowrap>Monto Comision :</td>
      <td>
      	<INPUT type="text" name="E01PAGCOM" onkeypress="enterDecimal()" maxlength="15" size="15" value="<%=bolgaran.getE01PAGCOM()%>" onChange="RecalculateComm()" onblur="verifyNum(this)">
      </td>
      <td align=right nowrap>Reversa Comision :</td>
      <td>
      	<INPUT type="text" name="E01REVCOM" size="15" maxlength="15" value="<%=bolgaran.getE01REVCOM()%>" readonly>
      </td>
    </tr>
    <tr id="trclear">
      <td nowrap colspan="4">
      <table id="headTable" class="tableinfo" style="filter:";>
   		  <tr id="trdark">
      		<td nowrap align="center" >Concepto</td>
      		<td nowrap align="center" >Banco </td>
      		<td nowrap align="center" >Sucursal</td>
      		<td nowrap align="center" >Moneda</td>
      		<td nowrap align="center" >Referencia</td>
    	  </tr>
          <tr id="trclear">
            <td nowrap >
              <div align="center" nowrap>
                <input type="text" name="E01PAGEPC" value="<%= bolgaran.getE01PAGEPC().trim()%>" size="3" maxlength="3">
                <input type="hidden" name="E01PAGEGL" value="<%= bolgaran.getE01PAGEGL().trim()%>">
                <input type="text" name="E01PAGECO" size="25" maxlength="25" readonly value="<%= bolgaran.getE01PAGECO().trim()%>"
                  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01LCMBNK.value,'','E01PAGEGL','E01PAGEPC',document.forms[0].E01LCMACD.value); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="left">
                <input type="text" name="E01PAGEBK" size="2" maxlength="2" value="<%= bolgaran.getE01PAGEBK().trim()%>">
              </div>
            </td>
            <td nowrap >
              <div align="left">
                <input type="text" name="E01PAGEBR" size="3" maxlength="3" value="<%= bolgaran.getE01PAGEBR().trim()%>"
                oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01LCMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="center">
                <input type="text" name="E01PAGECY" size="3" maxlength="3" value="<%= bolgaran.getE01PAGECY().trim()%>"
                oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01LCMBNK.value,'','','',''); return false;">
              </div>
            </td>
            <td nowrap >
              <div align="center">
                <input type="text" name="E01PAGEAC" size="12" maxlength="12"  value="<%= bolgaran.getE01PAGEAC().trim()%>"
                oncontextmenu="showPopUp(acctHlpByClient,this.name,document.forms[0].E01LCMBNK.value,'',document.forms[0].E01LCMCUN.value,'','RT'); return false;">
              </div>
            </td>
          </tr>
   	  </table>
      </td>
     </tr>
   </table>
   </td>
  </tr>
  </table>
 <% } %>
 <P align="center"><INPUT type="submit" value="enviar" id="EIBSBTN"></P>

</FORM>
</BODY>
</html>
