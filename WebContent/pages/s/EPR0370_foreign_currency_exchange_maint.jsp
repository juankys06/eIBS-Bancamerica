<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" 	class= "datapro.eibs.beans.JBObjList"  			scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session"/>
<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR037001Message"  	scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<% 
int row = 0;

if (msgInst.getE01REQREF().equals("0")) {
	try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
	mtList.setCurrentRow(row);
	msgInst = (datapro.eibs.beans.EPR037001Message) mtList.getRecord();
}

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

<script language="Javascript1.1">
function checkFieldChange(oldvalue,newvalue) {
	if (oldvalue.toLowerCase() != newvalue.toLowerCase()) {
		document.forms[0].H01FLGWK2.value = "1";
	}
}

function goAction(opt) {
	document.forms[0].H01FLGWK1.value = opt;
	document.forms[0].submit();
}
</script>
</head>
<body>

<SCRIPT Language="Javascript">
  builtHPopUp();

function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
   showPopupHelp();
   }
</SCRIPT>


<h3 align="center">Compraventa Moneda Extranjera<BR>Liquidacion de Compra de Cheques de Remesa y Cobranza<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0370"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0370">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK1">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK2" value="<%= msgInst.getH01FLGWK2() %>">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= request.getParameter("ROW") %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Referencia : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01REQREF" readonly size="15" maxlength="15" value="<%= msgInst.getE01REQREF() %>">
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01REQBNK" readonly size="3" maxlength="2" value="<%= msgInst.getE01REQBNK() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REQBRN" readonly size="5" maxlength="3" value="<%= msgInst.getE01REQBRN() %>">
	      	<input type="text" name="E01REQBRM" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01REQBRM() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REQINS" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQINS() %>">
	      	<input type="text" name="E01REQINN" readonly size="35" maxlength="35"  value="<%= msgInst.getE01REQINN() %>">
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REQCCY" readonly size="5" maxlength="3" value="<%= msgInst.getE01REQCCY() %>">
	      	<input type="text" name="E01REQCCN" readonly size="35" maxlength="35"  value="<%= msgInst.getE01REQCCN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01REQCUS" readonly size="10" maxlength="9" value="<%= msgInst.getE01REQCUS() %>">
              <input type="text" name="E01REQCUN" readonly size="35" maxlength="35"  value="<%= msgInst.getE01REQCUN() %>"> 
          </td>
        </tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Numero del Cheque:</div>
			</td>
			<td nowrap width="60%">
              <input type="text" name="E01REQCHK" readonly size="11" maxlength="10" value="<%= msgInst.getE01REQCHK() %>">
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Banco del Cheque:</div>
			</td>
			<td nowrap width="60%">
              <input type="text" name="E01REQCHB" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQCHB() %>">
              <input type="text" name="E01REQCHN" readonly size="35" maxlength="35" value="<%= msgInst.getE01REQCHN() %>">  
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Motivo de Operacion:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQORN" readonly size="35" maxlength="35" value="<%= msgInst.getE01REQORN() %>" > 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Corresponsal:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCOR" readonly size="10" maxlength="9" value="<%= msgInst.getE01REQCOR() %>" > 
	      		<input type="text" name="E01REQCON" readonly size="35" maxlength="35" value="<%= msgInst.getE01REQCON() %>" >
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Fecha/Hora de Creacion:</div>
			</td>
			<td nowrap width="60%">
                <input type="text" name="E01REQCD1" readonly size="2" maxlength="2" value="<%= msgInst.getE01REQCD1() %>" >
                <input type="text" name="E01REQCD2" readonly size="2" maxlength="2" value="<%= msgInst.getE01REQCD2() %>" >
                <input type="text" name="E01REQCD3" readonly size="2" maxlength="2" value="<%= msgInst.getE01REQCD3() %>" >
				<input type="text" name="E01REQCRT" readonly size="8" maxlength="8" value="<%= Util.formatTime(msgInst.getE01REQCRT()) %>" > 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Usuario que creo:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCRU" readonly size="12" maxlength="10" value="<%= msgInst.getE01REQCRU() %>" > 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Tasa de Cambio:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQEXR" size="15" maxlength="11" value="<%= msgInst.getE01REQEXR() %>"
				onchange="checkFieldChange('<%=msgInst.getE01REQEXR()%>',document.forms[0].E01REQEXR.value)" onkeypress="enterDecimal()">
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">  
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Extranjera:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQFEA" readonly size="20" maxlength="15" value="<%= msgInst.getE01REQFEA() %>" > 
			</td>
		</tr>
		<%if (userPO.getPurpose().equals("Approval")) {%>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Local:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQLCA" readonly size="20" maxlength="15" value="<%= msgInst.getE01REQLCA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor de Comision:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCOA" readonly size="20" maxlength="15" value="<%= msgInst.getE01REQCOA() %>" readonly>
				<%if (!msgInst.getE01REQCOA().equals("0.00")) {%>
				<a href="javascript:GetComission(document.forms[0].E01REQREF.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				<%}%> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Total:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQTOA" readonly size="20" maxlength="15" value="<%= msgInst.getE01REQTOA() %>" readonly> 
			</td>
		</tr>
  		<%}%>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <TABLE  id="mainTable" class="tableinfo">
  <TR><TD>
   <table id="headTable" width="100%">
    <tr id="trdark" align="center"> 
      <td nowrap align="center" >Concepto</td>
      <td nowrap align="center" >Banco</td>
      <td nowrap align="center" >Sucursal</td>
      <td nowrap align="center" >Moneda</td>
      <td nowrap align="center" >Cuenta Cliente</td>
      <td nowrap align="center" >Valor</td>
    </tr>
    <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP1" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP1() %>" readonly>
          <input type="hidden" name="E01OFFGL1" value="<%= msgInst.getField("E01OFFGL1").getString().trim() %>">
          <input type="text" name="E01OFFCO1" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO1() %>"
          onChange="checkFieldChange('<%=msgInst.getE01OFFCO1()%>',document.forms[0].E01OFFCO1.value)"
		  onselect="checkFieldChange('<%=msgInst.getE01OFFCO1()%>',document.forms[0].E01OFFCO1.value)"
		  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01REQBNK.value,'','E01OFFGL1','E01OFFOP1','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK1" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK1() %>" 
			 onChange="checkFieldChange('<%=msgInst.getE01OFFBK1()%>',document.forms[0].E01OFFBK1.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBK1()%>',document.forms[0].E01OFFBK1.value)">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR1" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR1() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFBR1()%>',document.forms[0].E01OFFBR1.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBR1()%>',document.forms[0].E01OFFBR1.value)"
		     oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY1" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY1() %>" 
             onChange="checkFieldChange('<%=msgInst.getE01OFFCY1()%>',document.forms[0].E01OFFCY1.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFCY1()%>',document.forms[0].E01OFFCY1.value)"
		     oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC1" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC1() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFAC1()%>',document.forms[0].E01OFFAC1.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFAC1()%>',document.forms[0].E01OFFAC1.value)"
		     oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E01REQBNK.value,'',document.forms[0].E01REQCUS.value,'','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA1" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA1() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP2" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP2() %>" readonly>
          <input type="hidden" name="E01OFFGL2" value="<%= msgInst.getField("E01OFFGL2").getString().trim() %>">
          <input type="text" name="E01OFFCO2" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO2() %>"
          onChange="checkFieldChange('<%=msgInst.getE01OFFCO2()%>',document.forms[0].E01OFFCO2.value)"
		  onselect="checkFieldChange('<%=msgInst.getE01OFFCO2()%>',document.forms[0].E01OFFCO2.value)"
		  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01REQBNK.value,'','E01OFFGL2','E01OFFOP2','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK2" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK2() %>" 
			 onChange="checkFieldChange('<%=msgInst.getE01OFFBK2()%>',document.forms[0].E01OFFBK2.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBK2()%>',document.forms[0].E01OFFBK2.value)">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR2" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR2() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFBR2()%>',document.forms[0].E01OFFBR2.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBR2()%>',document.forms[0].E01OFFBR2.value)"
		     oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY2" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY2() %>" 
             onChange="checkFieldChange('<%=msgInst.getE01OFFCY2()%>',document.forms[0].E01OFFCY2.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFCY2()%>',document.forms[0].E01OFFCY2.value)"
		     oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC2" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC2() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFAC2()%>',document.forms[0].E01OFFAC2.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFAC2()%>',document.forms[0].E01OFFAC2.value)"
		     oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E01REQBNK.value,'',document.forms[0].E01REQCUS.value,'','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA2" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA2() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP3" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP3() %>" readonly>
          <input type="hidden" name="E01OFFGL3" value="<%= msgInst.getField("E01OFFGL3").getString().trim() %>">
          <input type="text" name="E01OFFCO3" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO3() %>"
          onChange="checkFieldChange('<%=msgInst.getE01OFFCO3()%>',document.forms[0].E01OFFCO3.value)"
		  onselect="checkFieldChange('<%=msgInst.getE01OFFCO3()%>',document.forms[0].E01OFFCO3.value)"
		  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01REQBNK.value,'','E01OFFGL3','E01OFFOP3','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK3" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK3() %>" 
			 onChange="checkFieldChange('<%=msgInst.getE01OFFBK3()%>',document.forms[0].E01OFFBK3.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBK3()%>',document.forms[0].E01OFFBK3.value)">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR3" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR3() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFBR3()%>',document.forms[0].E01OFFBR3.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBR3()%>',document.forms[0].E01OFFBR3.value)"
		     oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY3" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY3() %>" 
             onChange="checkFieldChange('<%=msgInst.getE01OFFCY3()%>',document.forms[0].E01OFFCY3.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFCY3()%>',document.forms[0].E01OFFCY3.value)"
		     oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC3" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC3() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFAC3()%>',document.forms[0].E01OFFAC3.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFAC3()%>',document.forms[0].E01OFFAC3.value)"
		     oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E01REQBNK.value,'',document.forms[0].E01REQCUS.value,'','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA3" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA3() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP4" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP4() %>" readonly>
          <input type="hidden" name="E01OFFGL4" value="<%= msgInst.getField("E01OFFGL4").getString().trim() %>">
          <input type="text" name="E01OFFCO4" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO4() %>"
          onChange="checkFieldChange('<%=msgInst.getE01OFFCO4()%>',document.forms[0].E01OFFCO4.value)"
		  onselect="checkFieldChange('<%=msgInst.getE01OFFCO4()%>',document.forms[0].E01OFFCO4.value)"
		  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01REQBNK.value,'','E01OFFGL4','E01OFFOP4','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK4" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK4() %>" 
			 onChange="checkFieldChange('<%=msgInst.getE01OFFBK4()%>',document.forms[0].E01OFFBK4.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBK4()%>',document.forms[0].E01OFFBK4.value)">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR4" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR4() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFBR4()%>',document.forms[0].E01OFFBR4.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBR4()%>',document.forms[0].E01OFFBR4.value)"
		     oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY4" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY4() %>" 
             onChange="checkFieldChange('<%=msgInst.getE01OFFCY4()%>',document.forms[0].E01OFFCY4.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFCY4()%>',document.forms[0].E01OFFCY4.value)"
		     oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC4" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC4() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFAC4()%>',document.forms[0].E01OFFAC4.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFAC4()%>',document.forms[0].E01OFFAC4.value)"
		     oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E01REQBNK.value,'',document.forms[0].E01REQCUS.value,'','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA4" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA4() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP5" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP5() %>" readonly>
          <input type="hidden" name="E01OFFGL5" value="<%= msgInst.getField("E01OFFGL5").getString().trim() %>">
          <input type="text" name="E01OFFCO5" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO5() %>"
          onChange="checkFieldChange('<%=msgInst.getE01OFFCO5()%>',document.forms[0].E01OFFCO5.value)"
		  onselect="checkFieldChange('<%=msgInst.getE01OFFCO5()%>',document.forms[0].E01OFFCO5.value)"
		  oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E01REQBNK.value,'','E01OFFGL5','E01OFFOP5','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK5" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK5() %>" 
			 onChange="checkFieldChange('<%=msgInst.getE01OFFBK5()%>',document.forms[0].E01OFFBK5.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBK5()%>',document.forms[0].E01OFFBK5.value)">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR5" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR5() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFBR5()%>',document.forms[0].E01OFFBR5.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFBR5()%>',document.forms[0].E01OFFBR5.value)"
		     oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY5" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY5() %>" 
             onChange="checkFieldChange('<%=msgInst.getE01OFFCY5()%>',document.forms[0].E01OFFCY5.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFCY5()%>',document.forms[0].E01OFFCY5.value)"
		     oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC5" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC5() %>"
             onChange="checkFieldChange('<%=msgInst.getE01OFFAC5()%>',document.forms[0].E01OFFAC5.value)"
		     onselect="checkFieldChange('<%=msgInst.getE01OFFAC5()%>',document.forms[0].E01OFFAC5.value)"
		     oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E01REQBNK.value,'',document.forms[0].E01REQCUS.value,'','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA5" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA5() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
     </table>       
   </TD>  
</TR>	
</TABLE>

  <%if (userPO.getPurpose().equals("Approval")) {
  	userPO.setPurpose("");
  %>
  <br>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="20%">
				<div align="right">&nbsp;</div>
			</td>
			<td nowrap width="40%">
				<div align="center">Limite Minimo</div>
			</td>
			<td nowrap width="40%">
				<div align="center">Limite Maximo</div>
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">de Operacion</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQILL" size="15" maxlength="15" value="<%= msgInst.getE01REQILL() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQIML" size="15" maxlength="15" value="<%= msgInst.getE01REQIML() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">por Operacion/Corresponsal</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCLL" size="15" maxlength="15" value="<%= msgInst.getE01REQCLL() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCML" size="15" maxlength="15" value="<%= msgInst.getE01REQCML() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">&nbsp;</div>
			</td>
			<td nowrap width="40%" align="center">
				<div align="center">Limite</div>
			</td>
			<td nowrap width="40%" align="center">
				<div align="center">Acumulado</div>
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Diario por Cliente:</div>
			</td>
			<td nowrap width="40%" align="center" align="center">
				<input type="text" name="E01REQIMD" size="15" maxlength="15" value="<%= msgInst.getE01REQIMD() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center" align="center">
                <input type="text" name="E01REQICD" size="15" maxlength="15" value="<%= msgInst.getE01REQICD() %>" readonly>
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Mensual por Cliente:</div>
			</td>
			<td nowrap width="40%" align="center" align="center">
                <input type="text" name="E01REQIMM" size="15" maxlength="15" value="<%= msgInst.getE01REQIMM() %>" readonly>
			</td>
			<td nowrap width="40%" align="center" align="center">
				<input type="text" name="E01REQICM" size="15" maxlength="15" value="<%= msgInst.getE01REQICM() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Semestral por Cliente:</div>
			</td>
			<td nowrap width="40%" align="center" align="center">
				<input type="text" name="E01REQIMS" size="15" maxlength="15" value="<%= msgInst.getE01REQIMS() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center" align="center">
				<input type="text" name="E01REQICS" size="15" maxlength="15" value="<%= msgInst.getE01REQICS() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Anual por Cliente:</div>
			</td>
			<td nowrap width="40%" align="center" align="center">
				<input type="text" name="E01REQIMA" size="15" maxlength="15" value="<%= msgInst.getE01REQIMA() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center" align="center">
				<input type="text" name="E01REQICA" size="15" maxlength="15" value="<%= msgInst.getE01REQICA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Diario por Corresponsal:</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCMD" size="15" maxlength="15" value="<%= msgInst.getE01REQCMD() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
                <input type="text" name="E01REQCCD" size="15" maxlength="15" value="<%= msgInst.getE01REQCCD() %>" readonly>
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Mensual por Corresponsal:</div>
			</td>
			<td nowrap width="40%" align="center">
                <input type="text" name="E01REQCMM" size="15" maxlength="15" value="<%= msgInst.getE01REQCMM() %>" readonly>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCCM" size="15" maxlength="15" value="<%= msgInst.getE01REQCCM() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Semestral por Corresponsal:</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCMS" size="15" maxlength="15" value="<%= msgInst.getE01REQCMS() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCCS" size="15" maxlength="15" value="<%= msgInst.getE01REQCCS() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Anual por Corresponsal:</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCMA" size="15" maxlength="15" value="<%= msgInst.getE01REQCMA() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQCCA" size="15" maxlength="15" value="<%= msgInst.getE01REQCCA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Diario por Oficina:</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQBMD" size="15" maxlength="15" value="<%= msgInst.getE01REQBMD() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
                <input type="text" name="E01REQBCD" size="15" maxlength="15" value="<%= msgInst.getE01REQBCD() %>" readonly>
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Mensual por Oficina:</div>
			</td>
			<td nowrap width="40%" align="center">
                <input type="text" name="E01REQBMM" size="15" maxlength="15" value="<%= msgInst.getE01REQBMM() %>" readonly>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQBCM" size="15" maxlength="15" value="<%= msgInst.getE01REQBCM() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Semestral por Oficina:</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQBMS" size="15" maxlength="15" value="<%= msgInst.getE01REQBMS() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQBCS" size="15" maxlength="15" value="<%= msgInst.getE01REQBCS() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="20%">
				<div align="right">Anual por Oficina:</div>
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQBMA" size="15" maxlength="15" value="<%= msgInst.getE01REQBMA() %>" readonly> 
			</td>
			<td nowrap width="40%" align="center">
				<input type="text" name="E01REQBCA" size="15" maxlength="15" value="<%= msgInst.getE01REQBCA() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <%}%>
  <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Validar" onclick="goAction('P')">
    <input id="EIBSBTN" type="button" name="Submit" value="Salvar" onclick="goAction('L')">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="top.close()">
  </p>
  
  <script language="JavaScript">
	  //document.forms[0].E01OFFCO1.focus();
	  //document.forms[0].E01OFFCO1.select();
  </script>
  
</form>
</body>
</html>
