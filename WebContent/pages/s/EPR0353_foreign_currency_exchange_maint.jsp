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
<jsp:useBean id= "msgFrac" 	class= "datapro.eibs.beans.EPR035302Message"  	scope="session" />
<% 
int row = 0;
try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
mtList.setCurrentRow(row);
datapro.eibs.beans.EPR035301Message msgInst = (datapro.eibs.beans.EPR035301Message) mtList.getRecord();
int MNT = 0;
try { MNT = Integer.parseInt(request.getParameter("MNT"));} catch (Exception e) {}
if (MNT == 1) {
msgFrac.destroy();
}
msgFrac.setE02REQROR(msgInst.getE01REQROR());
msgFrac.setE02REQBNK(msgInst.getE01REQBNK());
msgFrac.setE02REQBRN(msgInst.getE01REQBRN());
msgFrac.setE02REQBRM(msgInst.getE01REQBRM());
msgFrac.setE02REQCCY(msgInst.getE01REQCCY());
msgFrac.setE02REQCCN(msgInst.getE01REQCCN());
msgFrac.setE02REQCUS(msgInst.getE01REQCUS());
msgFrac.setE02REQCUN(msgInst.getE01REQCUN());
msgFrac.setE02REQOPR(msgInst.getE01REQOPR());
msgFrac.setE02REQORN(msgInst.getE01REQORN());
msgFrac.setE02REQCOR(msgInst.getE01REQCOR());
msgFrac.setE02REQCON(msgInst.getE01REQCON());
msgFrac.setE02REQVD1(msgInst.getE01REQVD1());
msgFrac.setE02REQVD2(msgInst.getE01REQVD2());
msgFrac.setE02REQVD3(msgInst.getE01REQVD3());
msgFrac.setE02ORIOP1(msgInst.getE01OFFOP1());
msgFrac.setE02ORIGL1(msgInst.getE01OFFGL1());
msgFrac.setE02ORICO1(msgInst.getE01OFFCO1());
msgFrac.setE02ORIBK1(msgInst.getE01OFFBK1());
msgFrac.setE02ORIBR1(msgInst.getE01OFFBR1());
msgFrac.setE02ORICY1(msgInst.getE01OFFCY1());
msgFrac.setE02ORIAC1(msgInst.getE01OFFAC1());
msgFrac.setE02ORIVA1(msgInst.getE01OFFVA1());
msgFrac.setE02ORIOP2(msgInst.getE01OFFOP2());
msgFrac.setE02ORIGL2(msgInst.getE01OFFGL2());
msgFrac.setE02ORICO2(msgInst.getE01OFFCO2());
msgFrac.setE02ORIBK2(msgInst.getE01OFFBK2());
msgFrac.setE02ORIBR2(msgInst.getE01OFFBR2());
msgFrac.setE02ORICY2(msgInst.getE01OFFCY2());
msgFrac.setE02ORIAC2(msgInst.getE01OFFAC2());
msgFrac.setE02ORIVA2(msgInst.getE01OFFVA2());
msgFrac.setE02ORIOP3(msgInst.getE01OFFOP3());
msgFrac.setE02ORIGL3(msgInst.getE01OFFGL3());
msgFrac.setE02ORICO3(msgInst.getE01OFFCO3());
msgFrac.setE02ORIBK3(msgInst.getE01OFFBK3());
msgFrac.setE02ORIBR3(msgInst.getE01OFFBR3());
msgFrac.setE02ORICY3(msgInst.getE01OFFCY3());
msgFrac.setE02ORIAC3(msgInst.getE01OFFAC3());
msgFrac.setE02ORIVA3(msgInst.getE01OFFVA3());
msgFrac.setE02ORIOP4(msgInst.getE01OFFOP4());
msgFrac.setE02ORIGL4(msgInst.getE01OFFGL4());
msgFrac.setE02ORICO4(msgInst.getE01OFFCO4());
msgFrac.setE02ORIBK4(msgInst.getE01OFFBK4());
msgFrac.setE02ORIBR4(msgInst.getE01OFFBR4());
msgFrac.setE02ORICY4(msgInst.getE01OFFCY4());
msgFrac.setE02ORIAC4(msgInst.getE01OFFAC4());
msgFrac.setE02ORIVA4(msgInst.getE01OFFVA4());
msgFrac.setE02ORIOP5(msgInst.getE01OFFOP5());
msgFrac.setE02ORIGL5(msgInst.getE01OFFGL5());
msgFrac.setE02ORICO5(msgInst.getE01OFFCO5());
msgFrac.setE02ORIBK5(msgInst.getE01OFFBK5());
msgFrac.setE02ORIBR5(msgInst.getE01OFFBR5());
msgFrac.setE02ORICY5(msgInst.getE01OFFCY5());
msgFrac.setE02ORIAC5(msgInst.getE01OFFAC5());
msgFrac.setE02ORIVA5(msgInst.getE01OFFVA5());
msgFrac.setE02ORIINO(msgInst.getE01REQINO());
msgFrac.setE02ORIINP(msgInst.getE01REQINP());
msgFrac.setE02ORIFEA(msgInst.getE01REQFEA());
msgFrac.setE02ORIEXR(msgInst.getE01REQEXR());
msgFrac.setE02ORILCA(msgInst.getE01REQLCA());
msgFrac.setE02ORICOA(msgInst.getE01REQCOA());
msgFrac.setE02ORITOA(msgInst.getE01REQTOA());
msgFrac.setE02REQAAD(msgInst.getE01REQAAD());
msgFrac.setE02REQALD(msgInst.getE01REQALD());
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
function GetInfo(currentrow){
    var winH = (currentrow == "") ? 300 : 400;
	pg= prefix +language + "EPR0353_foreign_currency_exchange_conceptos.jsp?CurrRow="+ currentrow;
	CenterNamedWindow(pg,'Information',300,winH,2);
}

</script>

</head>
<body>

<H3 align="center">Fraccionamiento de Operaciones<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0353"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0353">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="500">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK1" VALUE="V">
  <INPUT TYPE=HIDDEN NAME="E02REQOPC" VALUE="S">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK2" value="<%= msgFrac.getH02FLGWK2() %>">
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
	      	<input type="text" name="E02REQROR" size="15" value="<%= msgFrac.getE02REQROR() %>" readonly>
		  </td>
     	</tr>    
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E02REQBNK" size="3" maxlength="2" value="<%= msgFrac.getE02REQBNK() %>" readonly>
	      </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E02REQBRN" size="5" maxlength="3" value="<%= msgFrac.getE02REQBRN() %>" readonly>
	      	<input type="text" name="E02REQBRM" readonly size="35" maxlength="35" readonly value="<%= msgFrac.getE02REQBRM() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E02REQCCY" size="5" maxlength="3" value="<%= msgFrac.getE02REQCCY() %>" readonly>
      	    <input type="text" name="E02REQCCN" readonly size="35" maxlength="35" readonly value="<%= msgFrac.getE02REQCCN() %>"> 
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
             <input type="text" name="E02REQCUS" size="10" maxlength="9" value="<%= msgFrac.getE02REQCUS() %>" readonly> 
             <input type="text" name="E02REQCUN" size="35" maxlength="35" value="<%= msgFrac.getE02REQCUN() %>" readonly> 
          </td>
        </tr>
        <tr id=trdark>
			<td nowrap>
				<div align="right">Motivo de Operacion:</div>
			</td>
			<td nowrap>
				<input type="text" name="E02REQOPR" size="5" maxlength="4" value="<%= msgFrac.getE02REQOPR() %>" readonly> 
				<input type="text"  name="E02REQORN" size="35" maxlength="35" value="<%= msgFrac.getE02REQORN() %>" readonly>
			</td>
		</tr>
		<tr id=trclear>
			<td nowrap>
				<div align="right">Corresponsal:</div>
			</td>
			<td nowrap>
				<input type="text" name="E02REQCOR" size="10" maxlength="9" value="<%= msgFrac.getE02REQCOR() %>" readonly> 
	      		<input type="text" name="E02REQCON" readonly size="35" maxlength="35" value="<%= msgFrac.getE02REQCON() %>" readonly>
			</td>
		</tr>
		<tr id=trdark>
			<td nowrap>
				<div align="right">AAD:</div>
			</td>
			<td nowrap>
				<input type="text" name="E02REQAAD" size="12" maxlength="10" value="<%= msgFrac.getE02REQAAD() %>" readonly> 
			</td>
		</tr>
		<tr id=trclear>
			<td nowrap>
				<div align="right">ALD:</div>
			</td>
			<td nowrap>
				<input type="text" name="E02REQALD" size="12" maxlength="10" value="<%= msgFrac.getE02REQALD() %>" readonly> 
			</td>
		</tr>
		<tr id=trdark>
			<td nowrap>
				<div align="right">Fecha de Liquidacion SPOT:</div>
			</td>
			<td nowrap>
                <input type="text" name="E02REQVD1" size="2" maxlength="2" value="<%= msgFrac.getE02REQVD1() %>" readonly>
                <input type="text" name="E02REQVD2" size="2" maxlength="2" value="<%= msgFrac.getE02REQVD2() %>" readonly>
                <input type="text" name="E02REQVD3" size="2" maxlength="2" value="<%= msgFrac.getE02REQVD3() %>" readonly>
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
			<td align="center">Instrumento Original</td>
			<td align="center">Valor Moneda Extranjera</td>
			<td align="center">Tasa de Cambio</td>
			<td align="center">Valor Moneda Local</td>
			<td align="center">Comision</td>
			<td align="center">Valor Total</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02ORIINO" size="5" maxlength="4" value="<%= msgFrac.getE02ORIINO() %>" readonly>
	      		<input type="text" name="E02ORIINP" size="25" maxlength="35" value="<%= msgFrac.getE02ORIINP() %>" readonly >
			</td>
			<td>
				<input type="text" name="E02ORIFEA" size="15" maxlength="15" value="<%= msgFrac.getE02ORIFEA() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02ORIEXR" size="20" maxlength="15" value="<%= msgFrac.getE02ORIEXR() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02ORILCA" size="20" maxlength="15" value="<%= msgFrac.getE02ORILCA() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02ORICOA" size="20" maxlength="15" value="<%= msgFrac.getE02ORICOA() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02ORITOA" size="20" maxlength="15" value="<%= msgFrac.getE02ORITOA() %>" readonly> 
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
    <%if (!msgFrac.getE02ORIVA1().equals("0.00")) {%> 
    <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02ORIOP1" size="3" maxlength="2" value="<%= msgFrac.getE02ORIOP1() %>" readonly>
          <input type="hidden" name="E02ORIGL1" value="<%= msgFrac.getE02ORIGL1() %>">
          <input type="text" name="E02ORICO1" size="25" maxlength="25" readonly value="<%= msgFrac.getE02ORICO1() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02ORIGL1','E02ORIOP1','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIBK1" size="2" maxlength="2" value="<%= msgFrac.getE02ORIBK1() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORIBR1" size="3" maxlength="3" value="<%= msgFrac.getE02ORIBR1() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORICY1" size="3" maxlength="3" value="<%= msgFrac.getE02ORICY1() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIAC1" size="12" maxlength="12"  value="<%= msgFrac.getE02ORIAC1() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIVA1" size="18" maxlength="15"  value="<%= msgFrac.getE02ORIVA1() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgFrac.getE02ORIVA2().equals("0.00")) {%> 
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02ORIOP2" size="3" maxlength="2" value="<%= msgFrac.getE02ORIOP2() %>" readonly>
          <input type="hidden" name="E02ORIGL2" value="<%= msgFrac.getE02ORIGL2() %>">
          <input type="text" name="E02ORICO2" size="25" maxlength="25" readonly value="<%= msgFrac.getE02ORICO2() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02ORIGL2','E02ORIOP2','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIBK2" size="2" maxlength="2" value="<%= msgFrac.getE02ORIBK2() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORIBR2" size="3" maxlength="3" value="<%= msgFrac.getE02ORIBR2() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORICY2" size="3" maxlength="3" value="<%= msgFrac.getE02ORICY2() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIAC2" size="12" maxlength="12"  value="<%= msgFrac.getE02ORIAC2() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIVA2" size="18" maxlength="15"  value="<%= msgFrac.getE02ORIVA2() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgFrac.getE02ORIVA3().equals("0.00")) {%> 
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02ORIOP3" size="3" maxlength="2" value="<%= msgFrac.getE02ORIOP3() %>" readonly>
          <input type="hidden" name="E02ORIGL3" value="<%= msgFrac.getE02ORIGL3() %>">
          <input type="text" name="E02ORICO3" size="25" maxlength="25" readonly value="<%= msgFrac.getE02ORICO3() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02ORIGL3','E02ORIOP3','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIBK3" size="2" maxlength="2" value="<%= msgFrac.getE02ORIBK3() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORIBR3" size="3" maxlength="3" value="<%= msgFrac.getE02ORIBR3() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORICY3" size="3" maxlength="3" value="<%= msgFrac.getE02ORICY3() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIAC3" size="12" maxlength="12"  value="<%= msgFrac.getE02ORIAC3() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIVA3" size="18" maxlength="15"  value="<%= msgFrac.getE02ORIVA3() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgFrac.getE02ORIVA4().equals("0.00")) {%> 
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02ORIOP4" size="3" maxlength="2" value="<%= msgFrac.getE02ORIOP4() %>" readonly>
          <input type="hidden" name="E02ORIGL4" value="<%= msgFrac.getE02ORIGL4() %>">
          <input type="text" name="E02ORICO4" size="25" maxlength="25" readonly value="<%= msgFrac.getE02ORICO4() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02ORIGL4','E02ORIOP4','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIBK4" size="2" maxlength="2" value="<%= msgFrac.getE02ORIBK4() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORIBR4" size="3" maxlength="3" value="<%= msgFrac.getE02ORIBR4() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORICY4" size="3" maxlength="3" value="<%= msgFrac.getE02ORICY4() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIAC4" size="12" maxlength="12"  value="<%= msgFrac.getE02ORIAC4() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIVA4" size="18" maxlength="15"  value="<%= msgFrac.getE02ORIVA4() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgFrac.getE02ORIVA5().equals("0.00")) {%> 
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
          <input type="text" name="E02ORIOP5" size="3" maxlength="2" value="<%= msgFrac.getE02ORIOP5() %>" readonly>
          <input type="hidden" name="E02ORIGL5" value="<%= msgFrac.getE02ORIGL5() %>">
          <input type="text" name="E02ORICO5" size="25" maxlength="25" readonly value="<%= msgFrac.getE02ORICO5() %>"
          oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02ORIGL5','E02ORIOP5','34'); return false;">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIBK5" size="2" maxlength="2" value="<%= msgFrac.getE02ORIBK5() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORIBR5" size="3" maxlength="3" value="<%= msgFrac.getE02ORIBR5() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E02ORICY5" size="3" maxlength="3" value="<%= msgFrac.getE02ORICY5() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIAC5" size="12" maxlength="12"  value="<%= msgFrac.getE02ORIAC5() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E02ORIVA5" size="18" maxlength="15"  value="<%= msgFrac.getE02ORIVA5() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
     </table>       
   </TD>  
</TR>	
</TABLE>    
  <br>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td align="center">Instrumento Nuevo</td>
			<td align="center">Valor Moneda Extranjera</td>
			<td align="center">Tasa de Cambio</td>
			<td align="center">Valor Moneda Local</td>
			<td align="center">Comision</td>
			<td align="center">Valor Total</td>
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN1" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN1() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN1()%>',document.forms[0].E02REQIN1.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN1()%>',document.forms[0].E02REQIN1.value)">
	      		<input type="text" name="E02REQID1" size="35" maxlength="35" value="<%= msgFrac.getE02REQID1() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN1','E02REQID1','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE1" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE1() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE1()%>',document.forms[0].E02REQFE1.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE1()%>',document.forms[0].E02REQFE1.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX1" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX1() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC1" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC1() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO1" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO1() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO1" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO1() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO1().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('1')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%> 
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN2" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN2() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN2()%>',document.forms[0].E02REQIN2.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN2()%>',document.forms[0].E02REQIN2.value)">
	      		<input type="text" name="E02REQID2" size="35" maxlength="35" value="<%= msgFrac.getE02REQID2() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN2','E02REQID2','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE2" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE2() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE2()%>',document.forms[0].E02REQFE2.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE2()%>',document.forms[0].E02REQFE2.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX2" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX2() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC2" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC2() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO2" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO2() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO2" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO2() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO2().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('2')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%>  
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN3" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN3() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN3()%>',document.forms[0].E02REQIN3.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN3()%>',document.forms[0].E02REQIN3.value)">
	      		<input type="text" name="E02REQID3" size="35" maxlength="35" value="<%= msgFrac.getE02REQID3() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN3','E02REQID3','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE3" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE3() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE3()%>',document.forms[0].E02REQFE3.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE3()%>',document.forms[0].E02REQFE3.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX3" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX3() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC3" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC3() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO3" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO3() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO3" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO3() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO3().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('3')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%> 
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN4" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN4() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN4()%>',document.forms[0].E02REQIN4.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN4()%>',document.forms[0].E02REQIN4.value)">
	      		<input type="text" name="E02REQID4" size="35" maxlength="35" value="<%= msgFrac.getE02REQID4() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN4','E02REQID4','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE4" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE4() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE4()%>',document.forms[0].E02REQFE4.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE4()%>',document.forms[0].E02REQFE4.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX4" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX4() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC4" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC4() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO4" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO4() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO4" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO4() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO4().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('4')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%>  
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN5" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN5() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN5()%>',document.forms[0].E02REQIN5.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN5()%>',document.forms[0].E02REQIN5.value)">
	      		<input type="text" name="E02REQID5" size="35" maxlength="35" value="<%= msgFrac.getE02REQID5() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN5','E02REQID5','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE5" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE5() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE5()%>',document.forms[0].E02REQFE5.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE5()%>',document.forms[0].E02REQFE5.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX5" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX5() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC5" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC5() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO5" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO5() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO5" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO5() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO5().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('5')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%> 
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN6" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN6() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN6()%>',document.forms[0].E02REQIN6.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN6()%>',document.forms[0].E02REQIN6.value)">
	      		<input type="text" name="E02REQID6" size="35" maxlength="35" value="<%= msgFrac.getE02REQID6() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN6','E02REQID6','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE6" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE6() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE6()%>',document.forms[0].E02REQFE6.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE6()%>',document.forms[0].E02REQFE6.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX6" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX6() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC6" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC6() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO6" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO6() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO6" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO6() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO6().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('6')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%>  
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN7" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN7() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN7()%>',document.forms[0].E02REQIN7.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN7()%>',document.forms[0].E02REQIN7.value)">
	      		<input type="text" name="E02REQID7" size="35" maxlength="35" value="<%= msgFrac.getE02REQID7() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN7','E02REQID7','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE7" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE7() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE7()%>',document.forms[0].E02REQFE7.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE7()%>',document.forms[0].E02REQFE7.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX7" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX7() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC7" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC7() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO7" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO7() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO7" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO7() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO7().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('7')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%> 
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN8" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN8() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN8()%>',document.forms[0].E02REQIN8.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN8()%>',document.forms[0].E02REQIN8.value)">
	      		<input type="text" name="E02REQID8" size="35" maxlength="35" value="<%= msgFrac.getE02REQID8() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN8','E02REQID8','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE8" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE8() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE8()%>',document.forms[0].E02REQFE8.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE8()%>',document.forms[0].E02REQFE8.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX8" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX8() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC8" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC8() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO8" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO8() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO8" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO8() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO8().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('8')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%> 
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN9" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN9() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN9()%>',document.forms[0].E02REQIN9.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN9()%>',document.forms[0].E02REQIN9.value)">
	      		<input type="text" name="E02REQID9" size="35" maxlength="35" value="<%= msgFrac.getE02REQID9() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN9','E02REQID9','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE9" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE9() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE9()%>',document.forms[0].E02REQFE9.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE9()%>',document.forms[0].E02REQFE9.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX9" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX9() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC9" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC9() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO9" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO9() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO9" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO9() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO9().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('9')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%>  
		</tr>
		<tr>
			<td>
				<input type="text" name="E02REQIN0" size="5" maxlength="4" value="<%= msgFrac.getE02REQIN0() %>" 
	      		onChange="checkFieldChange('<%=msgFrac.getE02REQIN0()%>',document.forms[0].E02REQIN0.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQIN0()%>',document.forms[0].E02REQIN0.value)">
	      		<input type="text" name="E02REQID0" size="35" maxlength="35" value="<%= msgFrac.getE02REQID0() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E02REQIN0','E02REQID0','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
			</td>
			<td>
				<input type="text" name="E02REQFE0" size="15" maxlength="15" value="<%= msgFrac.getE02REQFE0() %>" 
				onChange="checkFieldChange('<%=msgFrac.getE02REQFE0()%>',document.forms[0].E02REQFE0.value)"
	      		onselect="checkFieldChange('<%=msgFrac.getE02REQFE0()%>',document.forms[0].E02REQFE0.value)"
	      		onkeypress="enterDecimal()"> 
			</td>
			<td>
				<input type="text" name="E02REQEX0" size="20" maxlength="15" value="<%= msgFrac.getE02REQEX0() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLC0" size="20" maxlength="15" value="<%= msgFrac.getE02REQLC0() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCO0" size="20" maxlength="15" value="<%= msgFrac.getE02REQCO0() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTO0" size="20" maxlength="15" value="<%= msgFrac.getE02REQTO0() %>" readonly> 
			</td>
			<%if (!msgFrac.getE02REQTO0().equals("0.00")) {%> 
			<td align="left" nowrap> <a href="javascript:GetInfo('0')" ><img src="<%=request.getContextPath()%>/images/s/info.gif" alt="Conceptos" align="absmiddle" border="0" ></a>
			<%}%> 
		</tr>
		<tr>
			<td align="center">
				<p><B>TOTALES</B></p>
			</td>
			<td colspan=2>
				<input type="text" name="E02REQFET" size="15" maxlength="15" value="<%= msgFrac.getE02REQFET() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQLCT" size="20" maxlength="15" value="<%= msgFrac.getE02REQLCT() %>" readonly> 
			</td>
			<td>
				<input type="text" name="E02REQCOT" size="20" maxlength="15" value="<%= msgFrac.getE02REQCOT() %>" readonly>
			</td>
			<td>
				<input type="text" name="E02REQTOT" size="20" maxlength="15" value="<%= msgFrac.getE02REQTOT() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Calcular" onclick="goAction('V')">
    <input id="EIBSBTN" type="button" name="Submit" value="Salvar" onclick="goAction('P')">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="top.close()">
  </p>
</form>
</body>
</html>
