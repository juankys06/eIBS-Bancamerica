<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session"/>
<jsp:useBean id= "msgCanje" class= "datapro.eibs.beans.EPR033302Message"  	scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
int row = 0;
try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
mtList.setCurrentRow(row);
datapro.eibs.beans.EPR033301Message msgInst = (datapro.eibs.beans.EPR033301Message) mtList.getRecord();

int MNT = 0;
try { MNT = Integer.parseInt(request.getParameter("MNT"));} catch (Exception e) {}

if (MNT == 1) {

msgCanje.destroy();

}

msgCanje.setE02REQINO(msgInst.getE01REQINO());
msgCanje.setE02REQINP(msgInst.getE01REQINP());
msgCanje.setE02REQROR(msgInst.getE01REQROR());
msgCanje.setE02REQOPC(msgInst.getE01REQOPC());
msgCanje.setE02REQOPN(msgInst.getE01REQOPN());
msgCanje.setE02REQBNK(msgInst.getE01REQBNK());
msgCanje.setE02REQBRN(msgInst.getE01REQBRN());
msgCanje.setE02REQBRM(msgInst.getE01REQBRM());
msgCanje.setE02REQCCY(msgInst.getE01REQCCY());
msgCanje.setE02REQCCN(msgInst.getE01REQCCN());
msgCanje.setE02REQCUS(msgInst.getE01REQCUS());
msgCanje.setE02REQCUN(msgInst.getE01REQCUN());
msgCanje.setE02REQFEA(msgInst.getE01REQFEA());
msgCanje.setE02REQEXR(msgInst.getE01REQEXR());
msgCanje.setE02REQAAD(msgInst.getE01REQAAD());
msgCanje.setE02REQALD(msgInst.getE01REQALD());
msgCanje.setE02REQCS1(msgInst.getE01REQCS1());
msgCanje.setE02REQCN1(msgInst.getE01REQCN1());
msgCanje.setE02REQBE1(msgInst.getE01REQBE1());
msgCanje.setE02REQCI1(msgInst.getE01REQCI1());
msgCanje.setE02REQCQ1(msgInst.getE01REQCQ1());
msgCanje.setE02REQCS2(msgInst.getE01REQCS2());
msgCanje.setE02REQCN2(msgInst.getE01REQCN2());
msgCanje.setE02REQBE2(msgInst.getE01REQBE2());
msgCanje.setE02REQCI2(msgInst.getE01REQCI2());
msgCanje.setE02REQCQ2(msgInst.getE01REQCQ2());
msgCanje.setE02REQCS3(msgInst.getE01REQCS3());
msgCanje.setE02REQCN3(msgInst.getE01REQCN3());
msgCanje.setE02REQBE3(msgInst.getE01REQBE3());
msgCanje.setE02REQCI3(msgInst.getE01REQCI3());
msgCanje.setE02REQCQ3(msgInst.getE01REQCQ3());
msgCanje.setE02REQCS4(msgInst.getE01REQCS4());
msgCanje.setE02REQCN4(msgInst.getE01REQCN4());
msgCanje.setE02REQBE4(msgInst.getE01REQBE4());
msgCanje.setE02REQCI4(msgInst.getE01REQCI4());
msgCanje.setE02REQCQ4(msgInst.getE01REQCQ4());
msgCanje.setE02REQCS5(msgInst.getE01REQCS5());
msgCanje.setE02REQCN5(msgInst.getE01REQCN5());
msgCanje.setE02REQBE5(msgInst.getE01REQBE5());
msgCanje.setE02REQCI5(msgInst.getE01REQCI5());
msgCanje.setE02REQCQ5(msgInst.getE01REQCQ5());

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
		document.forms[0].H02FLGWK2.value = "1";
	}
}

function goAction(opt) {
	document.forms[0].H02FLGWK1.value = opt;
	document.forms[0].submit();
}

function GetComission() {
	page = "<%=request.getContextPath()%>/pages/s/EPR0333_foreign_currency_exchange_comission.jsp";
  	CenterWindow(page,500,300,1);
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

<H3 align="center">Preliquidacion de Solicitudes de Canje de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0333"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0333">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK1" VALUE="V">
  <INPUT TYPE=HIDDEN NAME="E02REQOPC" VALUE="S">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK2" value="<%= msgCanje.getH02FLGWK2() %>">
  <INPUT TYPE=HIDDEN NAME="MOTIVO" VALUE="  ">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">
    	<tr id=trdark>
	      <td nowrap> 
	        <div align="right">Referencia Original
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQROR" size="15" value="<%= msgCanje.getE02REQROR() %>" readonly>
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Operacion : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQOPN" readonly size="18" maxlength="15" value="<%= msgCanje.getE02REQOPN() %>">
		  </td>
     	</tr>    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E02REQBNK" size="3" maxlength="2" value="<%= msgCanje.getE02REQBNK() %>" readonly>
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQBRN" size="5" maxlength="3" value="<%= msgCanje.getE02REQBRN() %>" readonly>
	      	<input type="text" name="E02REQBRM" readonly size="35" maxlength="35" readonly value="<%= msgCanje.getE02REQBRM() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Instrumento Original: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQINO" size="5" maxlength="4" value="<%= msgCanje.getE02REQINO() %>" readonly>
	      	<input type="text" name="E02REQINP" size="35" maxlength="35" value="<%= msgCanje.getE02REQINP() %>" readonly >
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02REQCCY" size="5" maxlength="3" value="<%= msgCanje.getE02REQCCY() %>" readonly>
      	    <input type="text" name="E02REQCCN" readonly size="35" maxlength="35" readonly value="<%= msgCanje.getE02REQCCN() %>"> 
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02REQCUS" size="10" maxlength="9" value="<%= msgCanje.getE02REQCUS() %>" readonly> 
             <input type="text" name="E02REQCUN" size="35" maxlength="35" value="<%= msgCanje.getE02REQCUN() %>" readonly> 
          </td>
        </tr>
        <tr id=trclear>
			<td nowrap>
				<div align="right">AAD:</div>
			</td>
			<td nowrap>
				<input type="text" name="E02REQAAD" size="12" maxlength="10" value="<%= msgCanje.getE02REQAAD() %>" readonly> 
			</td>
		</tr>
		<tr id=trdark>
			<td nowrap>
				<div align="right">ALD:</div>
			</td>
			<td nowrap>
				<input type="text" name="E02REQALD" size="12" maxlength="10" value="<%= msgCanje.getE02REQALD() %>" readonly> 
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
    <table id="headTable" width="100%">
    	<tr id="trdark" align="center"> 
      	<td nowrap align="center" >Codigo Denominacion</td>
      	<td nowrap align="center" >Nombre Denominacion</td>
      	<td nowrap align="center" >Beneficiario</td>
      	<td nowrap align="center" >Numero Inicial</td>
      	<td nowrap align="center" >Cantidad</td>
    	</tr>
    	<%if (!msgCanje.getE02REQCQ1().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E02REQCS1" readonly size="5" maxlength="4" value="<%= msgCanje.getE02REQCS1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCN1" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQCN1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQBE1" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQBE1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCI1" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCI1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCQ1" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCQ1() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgCanje.getE02REQCQ2().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E02REQCS2" readonly size="5" maxlength="4" value="<%= msgCanje.getE02REQCS2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCN2" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQCN2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQBE2" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQBE2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCI2" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCI2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCQ2" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCQ2() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgCanje.getE02REQCQ3().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E02REQCS3" readonly size="5" maxlength="4" value="<%= msgCanje.getE02REQCS3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCN3" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQCN3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQBE3" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQBE3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCI3" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCI3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCQ3" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCQ3() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgCanje.getE02REQCQ4().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E02REQCS4" readonly size="5" maxlength="4" value="<%= msgCanje.getE02REQCS4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCN4" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQCN4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQBE4" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQBE4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCI4" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCI4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCQ4" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCQ4() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgCanje.getE02REQCQ5().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E02REQCS5" readonly size="5" maxlength="4" value="<%= msgCanje.getE02REQCS5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCN5" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQCN5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQBE5" readonly size="40" maxlength="35" value="<%= msgCanje.getE02REQBE5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCI5" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCI5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E02REQCQ5" readonly size="10" maxlength="10" value="<%= msgCanje.getE02REQCQ5() %>">
	      	</td>
		</tr>
		<%}%>
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
	      <td nowrap> 
	        <div align="right">Instrumento Nuevo : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQINS" size="5" maxlength="4" value="<%= msgCanje.getE02REQINS() %>" 
	      		onChange="checkFieldChange('<%=msgCanje.getE02REQINS()%>',document.forms[0].E02REQINS.value)"
	      		onselect="checkFieldChange('<%=msgCanje.getE02REQINS()%>',document.forms[0].E02REQINS.value)">
	      	<input type="text" name="E02REQINN" size="35" maxlength="35" value="<%= msgCanje.getE02REQINN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E02REQINS','E02REQINN','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
		  </td>
     	</tr>    
		<tr>
			<td nowrap width="40%">
				<div align="right">Motivo de Operacion:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQOPR" size="5" maxlength="4" value="<%= msgCanje.getE02REQOPR() %>" 
					onChange="checkFieldChange('<%=msgCanje.getE02REQOPR()%>',document.forms[0].E02REQOPR.value)"
					onselect="checkFieldChange('<%=msgCanje.getE02REQOPR()%>',document.forms[0].E02REQOPR.value)"> 
				<input type="text"  name="E02REQORN" size="35" maxlength="35" value="<%= msgCanje.getE02REQORN() %>" readonly>
				<a href="javascript:GetCodeDescCNOFC('E02REQOPR','E02REQORN','Y!')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Corresponsal:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQCOR" size="10" maxlength="9" value="<%= msgCanje.getE02REQCOR() %>" 
					onChange="checkFieldChange('<%=msgCanje.getE02REQCOR()%>',document.forms[0].E02REQCOR.value)"
					onselect="checkFieldChange('<%=msgCanje.getE02REQCOR()%>',document.forms[0].E02REQCOR.value)"> 
	      		<input type="text" name="E02REQCON" readonly size="35" maxlength="35" value="<%= msgCanje.getE02REQCON() %>" readonly>
				<a href="javascript:GetCorrespondentDescId('E02REQCOR','E02REQCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Fecha de Liquidacion SPOT:</div>
			</td>
			<td nowrap width="60%">
                <input type="text" name="E02REQVD1" size="2" maxlength="2" value="<%= msgCanje.getE02REQVD1() %>" >
                <input type="text" name="E02REQVD2" size="2" maxlength="2" value="<%= msgCanje.getE02REQVD2() %>" >
                <input type="text" name="E02REQVD3" size="2" maxlength="2" value="<%= msgCanje.getE02REQVD3() %>" >
				<a href="javascript:DatePicker(document.forms[0].E02REQVD1,document.forms[0].E02REQVD2,document.forms[0].E02REQVD3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt=". . ." align="absbottom" border="0" ></a>
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
				<div align="right">Valor en Moneda Extranjera:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQFEA" size="15" maxlength="15" value="<%= msgCanje.getE02REQFEA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Tasa de Cambio:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQEXR" size="20" maxlength="15" value="<%= msgCanje.getE02REQEXR() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Local:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQLCA" size="20" maxlength="15" value="<%= msgCanje.getE02REQLCA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor de Comision:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQCOA" size="20" maxlength="15" value="<%= msgCanje.getE02REQCOA() %>" readonly>
				<%if (!msgCanje.getE02REQCOA().equals("0.00")) {%>
				<a href="javascript:GetComission()"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
				<%}%> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Total:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E02REQTOA" size="20" maxlength="15" value="<%= msgCanje.getE02REQTOA() %>" readonly> 
			</td>
		</tr>
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
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP1" size="3" maxlength="2" value="<%= msgCanje.getE02OFFOP1() %>" readonly>
          <input type="hidden" name="E02OFFGL1" value="<%= msgCanje.getE02OFFGL1() %>">
          <input type="text" name="E02OFFCO1" size="25" maxlength="25" readonly value="<%= msgCanje.getE02OFFCO1() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL1','E02OFFOP1','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK1" size="2" maxlength="2" value="<%= msgCanje.getE02OFFBK1() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR1" size="3" maxlength="3" value="<%= msgCanje.getE02OFFBR1() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY1" size="3" maxlength="3" value="<%= msgCanje.getE02OFFCY1() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC1" size="12" maxlength="12"  value="<%= msgCanje.getE02OFFAC1() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RT'); return false;">
          </div>
        </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA1" size="18" maxlength="15" value="<%= msgCanje.getE02OFFVA1() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP2" size="3" maxlength="2" value="<%= msgCanje.getField("E02OFFOP2").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL2" value="<%= msgCanje.getField("E02OFFGL2").getString().trim() %>">
          <input type="text" name="E02OFFCO2" size="25" maxlength="25" readonly value="<%= msgCanje.getField("E02OFFCO2").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL2','E02OFFOP2','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK2" size="2" maxlength="2" value="<%= msgCanje.getE02OFFBK2() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR2" size="3" maxlength="3" value="<%= msgCanje.getE02OFFBR2() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY2" size="3" maxlength="3" value="<%= msgCanje.getE02OFFCY2() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC2" size="12" maxlength="12"  value="<%= msgCanje.getE02OFFAC2() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RT'); return false;">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA2" size="18" maxlength="15" value="<%= msgCanje.getE02OFFVA2() %>" onkeypress="enterDecimal()">
          </div>
        </td>
      </tr>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP3" size="3" maxlength="2" value="<%= msgCanje.getField("E02OFFOP3").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL3" value="<%= msgCanje.getField("E02OFFGL3").getString().trim() %>">
          <input type="text" name="E02OFFCO3" size="25" maxlength="25" readonly value="<%= msgCanje.getField("E02OFFCO3").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL3','E02OFFOP3','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK3" size="2" maxlength="2" value="<%= msgCanje.getE02OFFBK3() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR3" size="3" maxlength="3" value="<%= msgCanje.getE02OFFBR3() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY3" size="3" maxlength="3" value="<%= msgCanje.getE02OFFCY3() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC3" size="12" maxlength="12"  value="<%= msgCanje.getE02OFFAC3() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA3" size="18" maxlength="15" value="<%= msgCanje.getE02OFFVA3() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP4" size="3" maxlength="2" value="<%= msgCanje.getField("E02OFFOP4").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL4" value="<%= msgCanje.getField("E02OFFGL4").getString().trim() %>">
          <input type="text" name="E02OFFCO4" size="25" maxlength="25" readonly value="<%= msgCanje.getField("E02OFFCO4").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL4','E02OFFOP4','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK4" size="2" maxlength="2" value="<%= msgCanje.getE02OFFBK4() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR4" size="3" maxlength="3" value="<%= msgCanje.getE02OFFBR4() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY4" size="3" maxlength="3" value="<%= msgCanje.getE02OFFCY4() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC4" size="12" maxlength="12"  value="<%= msgCanje.getE02OFFAC4() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RT'); return false;">
          </div>
        </td>
        <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA4" size="18" maxlength="15" value="<%= msgCanje.getE02OFFVA4() %>" onkeypress="enterDecimal()">
          </div>
        </td>
      </tr>
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02OFFOP5" size="3" maxlength="2" value="<%= msgCanje.getField("E02OFFOP5").getString().trim() %>" readonly>
          <input type="hidden" name="E02OFFGL5" value="<%= msgCanje.getField("E02OFFGL5").getString().trim() %>">
          <input type="text" name="E02OFFCO5" size="25" maxlength="25" readonly value="<%= msgCanje.getField("E02OFFCO5").getString().trim() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL5','E02OFFOP5','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFBK5" size="2" maxlength="2" value="<%= msgCanje.getE02OFFBK5() %>">
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFBR5" size="3" maxlength="3" value="<%= msgCanje.getE02OFFBR5() %>"
             oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02OFFCY5" size="3" maxlength="3" value="<%= msgCanje.getE02OFFCY5() %>"
             oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFAC5" size="12" maxlength="12"  value="<%= msgCanje.getE02OFFAC5() %>"
             oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RT'); return false;">
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02OFFVA5" size="18" maxlength="15" value="<%= msgCanje.getE02OFFVA5() %>" onkeypress="enterDecimal()">
          </div>
       </td>
      </tr>
     </table>       
   </TD>  
</TR>	
</TABLE>    
  
  <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Preliquidar" onclick="goAction('V')">
    <input id="EIBSBTN" type="button" name="Submit" value="Salvar" onclick="goAction('P')">
    <input id="EIBSBTN" type="button" name="Submit" value="Extracupo" onclick="goAction('E')">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="top.close()">
  </p>
  
  <script language="JavaScript">
	  document.forms[0].E02REQINS.focus();
	  document.forms[0].E02REQINS.select();
  </script>
  
</form>
</body>
</html>
