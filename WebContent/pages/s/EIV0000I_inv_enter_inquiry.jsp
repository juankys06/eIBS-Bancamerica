<html>
<head>
<title>Investment Inquiry</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>

<script language="JavaScript">

function closeHiddenDivs(){
	setVisibility(document.getElementById("showHiddenDivOption1"), "hidden");
	setVisibility(document.getElementById("showHiddenDivOption2"), "hidden");
	setVisibility(document.getElementById("showHiddenDivOption3"), "hidden");
}

function showHiddenDivOption1(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var showHiddenDivOption = document.getElementById("showHiddenDivOption1");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(showHiddenDivOption, y, x);
	setVisibility(showHiddenDivOption, "visible");
	 
}

function showHiddenDivOption2(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var showHiddenDivOption = document.getElementById("showHiddenDivOption2");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(showHiddenDivOption, y, x);
	setVisibility(showHiddenDivOption, "visible");
	 
}

function showHiddenDivOption3(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var showHiddenDivOption = document.getElementById("showHiddenDivOption3");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(showHiddenDivOption, y, x);
	setVisibility(showHiddenDivOption, "visible");
	 
}

document.onclick= closeHiddenDivs;

function Validate(){
  if(document.forms[0].CUSTOMER.value.length <1){ 
 
      if(document.forms[0].OPT.value=="1" || document.forms[0].OPT.value=="5"){
      alert("A valid customer number must be enter");
	  document.forms[0].CUSTOMER.focus();
      return;
   	  }
  }
  document.forms[0].submit();
}

function subInst(){
  document.forms[0].OPT.value = '9';
  document.forms[0].submit();
}

function subEvent(){
  document.forms[0].OPT.value = '10';
  document.forms[0].submit();
}

</SCRIPT>



</head>


<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<jsp:useBean id= "currUser" class= "datapro.eibs.beans.ESS0030DSMessage"  scope="session" />




<body bgcolor="#FFFFFF">

<H3 align="center"> Consulta - Inversiones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="inv_enter_inquiry,EIV0000I"></H3>

<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEIV0000I">
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>
<input type=HIDDEN name="SCREEN" value="200">
  <input type=HIDDEN name="OPT" value="1">
<div id="hiddenDivOption1" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Portfolio :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="PORTFOLIO" size="9" maxlength="9" >
         <a href="javascript:GetPortfolioNumDesc('PORTFOLIO','',document.forms[0].CUSTOMER.value)">
			<img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" >
		 </a>
	  </div>
      </td>
    </tr>
    <tr id="trclear"> 
      <td align=CENTER width="18%"> 
        <div align="right">Cash Account :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="CURRENCY" size="3" maxlength="3" 
                readonly >
        <input type="text" name="ACCOUNT" size="12" maxlength="9">
        <a href="javascript:GetCashAccount('CURRENCY','ACCOUNT','MEMBAL',document.forms[0].CUSTOMER.value,document.forms[0].PORTFOLIO.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"  ></a>
		<input type="text" name="MEMBAL" size="15" maxlength="15" 
                readonly >
	  </div>
      </td>
    </tr>
   <tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Settlement Date :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="DATE1" size="3" maxlength="2" >
         <input type="text" name="DATE2" size="3" maxlength="2" >
		 <input type="text" name="DATE3" size="3" maxlength="2" >
		 <a href="javascript:DatePicker(document.forms[0].DATE1,document.forms[0].DATE2,document.forms[0].DATE3)">
			<img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0">
		 </a>
	  </div>
      </td>
    </tr>
   <tr id="trclear">
   
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=submit name="Submit3" value="Submit" > 
   </TD>
   
   </tr>
 </TABLE>
</div> 

<div id="hiddenDivOption2" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Instrument :</div>
      </td>
      <td align=CENTER width="34%"> 
        <div align="left"> 
         <input type="text" name="CODE" size="5" maxlength="7" >
         <a href="javascript:GetInstrumentParams('CODE','','','','','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0">
		</a>
	  </div>
      </td>
    </tr>
   <tr id="trclear">
   
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=button name="Submit3" value="Submit" onClick="subInst()"> 
   </TD>
   
   </tr>
 </TABLE>
</div> 

<div id="hiddenDivOption3" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Show Event :</div>
      </td>
      <td align=LEFT width="34%"> 
       <select name="EVENT">
                <option value=" ">All </option>
                <option value="1">Interest</option>
                <option value="2">Maturity</option>
                <option value="3">Call</option>
                <option value="4">Put</option>
				<option value="5">Dividends</option>
          </select> 
      </td>
    </tr>
    <tr id="trclear"> 
      <td align=CENTER width="18%"> 
        <div align="right">Portfolio Type :</div>
      </td>
      <td align=LEFT width="34%"> 
       <select name="PRFTYPE">
                <option value=""></option>
                <option value="D">Discretionary </option>
                <option value="N">Non Discretionary</option>
          </select> 
      </td>
    </tr>
   <tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">From Date  :</div>
      </td>
      <td align=LEFT width="34%"> 
       <input type="text" size="2" maxlength="2" name="DATEF1" 
					  >
                      <input type="text" size="2" maxlength="2" name="DATEF2" onKeyPress="enterInteger()">
                      <input type="text" size="2" maxlength="2" name="DATEF3" onKeyPress="enterInteger()">
                      <a href="javascript:DatePicker(document.forms[0].DATEF1,document.forms[0].DATEF2,document.forms[0].DATEF3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                      To 
                      <input type="text" size="2" maxlength="2" name="DATET1" 
					  >
                      <input type="text" size="2" maxlength="2" name="DATET2" onKeyPress="enterInteger()">
                      <input type="text" size="2" maxlength="2" name="DATET3" onKeyPress="enterInteger()">
                      <a href="javascript:DatePicker(document.forms[0].DATET1,document.forms[0].DATET2,document.forms[0].DATET3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a>
      </td>
    </tr>
   
   <tr id="trclear">
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=button name="Submit3" value="Submit" onClick="subEvent()"> 
   </TD>
   
   </tr>
 </TABLE>
</div> 


  <center>
    <table border="0" class="tableinfo" width="100%">
      <tr id="trdark"> 
        <td colspan="3"> 
          <div align="center"><b> Consulta por Clientes</b></div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td colspan="3"> 
          <div align="center">Cliente : 
            <input type="text" name="CUSTOMER" size="9" maxlength="9">
            <input type="text" name="CUNDSC" size="25" maxlength="25">
            <a href="javascript:GetCustomerDescId('CUSTOMER','CUNDSC','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
          </div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="13%">&nbsp;</td>
        <td width="40%"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="1"
			onClick="document.forms[0].OPT.value ='1'" checked>
            Posici&oacute;n del Cliente</div>
        </td>
        <td width="47%"> 
          <input id="eibsOption1" type="radio" name="INPTOPT" value="2">Retenidos y Diferidos
         </td>
      </tr>
      <tr id="trclear">
        <td width="13%">&nbsp;</td>
        <td width="40%">
          <input type="radio" name="INPTOPT" value="12"
			onClick="document.forms[0].OPT.value ='12'">
          Posici&oacute;n Hist&oacute;rica</td>
        <td width="47%">
          <input type="radio" name="INPTOPT" value="5"
		  onClick="document.forms[0].OPT.value ='5'">
          Informaci&oacute;n del Cliente</td>
      </tr>
      <tr id="trclear"> 
        <td width="13%">&nbsp;</td>
        <td width="40%"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="3"
		    onClick="document.forms[0].OPT.value ='3'">
            Perf&iacute;l del Cliente</div>
        </td>
        <td width="47%">&nbsp; </td>
      </tr>
      <tr id="trdark"> 
        <td width="13%">&nbsp;</td>
        <td width="40%"> 
          <div align="center"><b>Consultas Generales</b></div>
        </td>
        <td width="47%"> 
          <div align="center"><b>Consulta Instrumentos</b></div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="13%">&nbsp;</td>
        <td width="40%"> 
          <input id="eibsOption2" type="radio" name="INPTOPT" value="10">Pr&oacute;xima Fecha Pago de Cup&oacute;n
        </td>
        <td width="47%"> 
          <input id="eibsOption3" type="radio" name="INPTOPT" value="9">Posici&oacute;n del Cliente por Instrumento
         </td>
      </tr>
      <tr id="trclear"> 
        <td width="13%">&nbsp;</td>
        <td width="40%"> 
          <input type="radio" name="INPTOPT" value="6"
		  onClick="document.forms[0].OPT.value ='6'">
          Orden de Compra - Venta</td>
        <td width="47%"> 
          <div align="left"> 
            <input type="radio" name="INPTOPT" value="8"
		  onClick="document.forms[0].OPT.value ='8'">
            Cupones Generados</div>
        </td>
      </tr>
      <tr id="trclear"> 
        <td width="13%">&nbsp;</td>
        <td width="40%">&nbsp; </td>
        <td width="47%"> 
          <input type="radio" name="INPTOPT" value="7"
		  onClick="document.forms[0].OPT.value ='7'">
          Informaci&oacute;n Instrumentos </td>
      </tr>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
      <tr bgcolor="#FFFFFF"> 
        <td width="33%">
		  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=button name="Submit" value="Enviar" onClick="Validate()">
          </div>
        </td>
      </tr>
      <tr bgcolor="#FFFFFF"> 
        <td> </td>
      </tr>
    </table>

    <br>
  </center>
  <center>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
  </center>
      
  
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
  <%
 }
%> <b>
 
  </b>
<SCRIPT Language="Javascript">
 	document.getElementById("hiddenDivOption1").onclick=cancelBub;
 	document.getElementById("hiddenDivOption2").onclick=cancelBub;
 	document.getElementById("hiddenDivOption3").onclick=cancelBub;
	document.getElementById("eibsOption1").onclick=showHiddenDivOption1;
	document.getElementById("eibsOption2").onclick=showHiddenDivOption2;
	document.getElementById("eibsOption3").onclick=showHiddenDivOption3;
</SCRIPT>

</form>
</body>
</html>
