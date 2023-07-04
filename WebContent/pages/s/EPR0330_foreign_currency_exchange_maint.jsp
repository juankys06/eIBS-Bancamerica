<html>  
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>
<jsp:useBean id= "mtList" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  	scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>
<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR033002Message"  	scope="session" />

<% 
int row = 0;
try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
mtList.setCurrentRow(row);
datapro.eibs.beans.EPR033001Message msgTemp = (datapro.eibs.beans.EPR033001Message) mtList.getRecord();
int MNT = 0;
try { MNT = Integer.parseInt(request.getParameter("MNT"));} catch (Exception e) {}
msgInst.setH02FLGWK2("0");
if (MNT == 1) {
msgInst.destroy();
msgInst.setE02REQREF(msgTemp.getE01REQREF());
msgInst.setE02REQOPC(msgTemp.getE01REQOPC());
msgInst.setE02REQINS(msgTemp.getE01REQINS());
msgInst.setE02REQINN(msgTemp.getE01REQINN());
msgInst.setE02REQBNK(msgTemp.getE01REQBNK());
msgInst.setE02REQBRN(msgTemp.getE01REQBRN());
msgInst.setE02REQBRM(msgTemp.getE01REQBRM());
msgInst.setE02REQCCY(msgTemp.getE01REQCCY());
msgInst.setE02REQCCN(msgTemp.getE01REQCCN());
msgInst.setE02REQCUS(msgTemp.getE01REQCUS());
msgInst.setE02REQCUN(msgTemp.getE01REQCUN());
msgInst.setE02REQOPR(msgTemp.getE01REQOPR());
msgInst.setE02REQORN(msgTemp.getE01REQORN());
msgInst.setE02REQCOR(msgTemp.getE01REQCOR());
msgInst.setE02REQCON(msgTemp.getE01REQCON());
msgInst.setE02REQVD1(msgTemp.getE01REQVD1());
msgInst.setE02REQVD2(msgTemp.getE01REQVD2());
msgInst.setE02REQVD3(msgTemp.getE01REQVD3());
msgInst.setE02REQFEA(msgTemp.getE01REQFEA());
msgInst.setE02REQEXR(msgTemp.getE01REQEXR());
msgInst.setE02REQLCA(msgTemp.getE01REQLCA());
msgInst.setE02REQCOA(msgTemp.getE01REQCOA());
msgInst.setE02REQTOA(msgTemp.getE01REQTOA());
msgInst.setE02OFFOP1(msgTemp.getE01OFFOP1());
msgInst.setE02OFFGL1(msgTemp.getE01OFFGL1());
msgInst.setE02OFFCO1(msgTemp.getE01OFFCO1());
msgInst.setE02OFFBK1(msgTemp.getE01OFFBK1());
msgInst.setE02OFFBR1(msgTemp.getE01OFFBR1());
msgInst.setE02OFFCY1(msgTemp.getE01OFFCY1());
msgInst.setE02OFFAC1(msgTemp.getE01OFFAC1());
msgInst.setE02OFFVA1(msgTemp.getE01OFFVA1());
msgInst.setE02OFFOP2(msgTemp.getE01OFFOP2());
msgInst.setE02OFFGL2(msgTemp.getE01OFFGL2());
msgInst.setE02OFFCO2(msgTemp.getE01OFFCO2());
msgInst.setE02OFFBK2(msgTemp.getE01OFFBK2());
msgInst.setE02OFFBR2(msgTemp.getE01OFFBR2());
msgInst.setE02OFFCY2(msgTemp.getE01OFFCY2());
msgInst.setE02OFFAC2(msgTemp.getE01OFFAC2());
msgInst.setE02OFFVA2(msgTemp.getE01OFFVA2());
msgInst.setE02OFFOP3(msgTemp.getE01OFFOP3());
msgInst.setE02OFFGL3(msgTemp.getE01OFFGL3());
msgInst.setE02OFFCO3(msgTemp.getE01OFFCO3());
msgInst.setE02OFFBK3(msgTemp.getE01OFFBK3());
msgInst.setE02OFFBR3(msgTemp.getE01OFFBR3());
msgInst.setE02OFFCY3(msgTemp.getE01OFFCY3());
msgInst.setE02OFFAC3(msgTemp.getE01OFFAC3());
msgInst.setE02OFFVA3(msgTemp.getE01OFFVA3());
msgInst.setE02OFFOP4(msgTemp.getE01OFFOP4());
msgInst.setE02OFFGL4(msgTemp.getE01OFFGL4());
msgInst.setE02OFFCO4(msgTemp.getE01OFFCO4());
msgInst.setE02OFFBK4(msgTemp.getE01OFFBK4());
msgInst.setE02OFFBR4(msgTemp.getE01OFFBR4());
msgInst.setE02OFFCY4(msgTemp.getE01OFFCY4());
msgInst.setE02OFFAC4(msgTemp.getE01OFFAC4());
msgInst.setE02OFFVA4(msgTemp.getE01OFFVA4());
msgInst.setE02OFFOP5(msgTemp.getE01OFFOP5());
msgInst.setE02OFFGL5(msgTemp.getE01OFFGL5());
msgInst.setE02OFFCO5(msgTemp.getE01OFFCO5());
msgInst.setE02OFFBK5(msgTemp.getE01OFFBK5());
msgInst.setE02OFFBR5(msgTemp.getE01OFFBR5());
msgInst.setE02OFFCY5(msgTemp.getE01OFFCY5());
msgInst.setE02OFFAC5(msgTemp.getE01OFFAC5());
msgInst.setE02OFFVA5(msgTemp.getE01OFFVA5());
msgInst.setE02REQAAD(msgTemp.getE01REQAAD());
msgInst.setE02REQALD(msgTemp.getE01REQALD());
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
		document.forms[0].H02FLGWK2.value = "1";
	}
}

function goAction(opt) {
	document.forms[0].H02FLGWK1.value = opt;
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
<H3 align="center">Modificacion de Solicitudes de Compraventa de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_maint, EPR0330"></H3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0330">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK1" VALUE="V">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK2" value="<%= msgInst.getH02FLGWK2() %>">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  <table class="tableinfo">
  	<tr> 
    	<td>
    		<table cellspacing=0 cellpadding=2 width="100%" border="0">
    			<tr id=trdark> 
	      			<td nowrap width="40%"> 
	       				 <div align="right">Referencia : </div>
	      			</td>
	      			<td nowrap width="60%"> 
	        			<input type="text" name="E02REQREF" readonly size="15" maxlength="15" value="<%= msgInst.getE02REQREF() %>">
	      			</td>
     			</tr>    
     			<tr id=trclear> 
	      			<td nowrap> 
	        			<div align="right">Operacion : </div>
	      			</td>
	      			<td nowrap>
	      				<select name="E02REQOPC">
          	  				<option value="Y*" selected <%if (msgInst.getE02REQOPC().equals("Y*")) { out.print("selected"); }%>>Compra</option>
                			<option value="Y!" <%if (msgInst.getE02REQOPC().equals("Y!") || msgInst.getE02REQOPC().equals("Y]")) { out.print("selected"); }%>>Venta</option>
           				</select>
		 			 </td>
     			</tr>
     			<tr id=trdark> 
	      			<td nowrap width="40%"> 
	        			<div align="right">Banco : </div>
	      			</td>
	      			<td nowrap width="60%"> 
	        			<input type="text" name="E02REQBNK" size="3" maxlength="2" value="<%= msgInst.getE02REQBNK() %>" readonly >
	     	 		</td>
     			</tr>
     			<tr id=trclear> 
	      			<td nowrap> 
	        			<div align="right">Oficina : </div>
	      			</td>
	      			<td nowrap>
	      				<input type="text" name="E02REQBRN" size="5" maxlength="3" value="<%= msgInst.getE02REQBRN() %>" readonly >
		  			</td>
     			</tr>
     			<tr id=trdark> 
	      			<td nowrap> 
	        			<div align="right">Instrumento : </div>
	      			</td>
	      			<td nowrap>
	      				<input type="text" name="E02REQINS" size="5" maxlength="4" value="<%= msgInst.getE02REQINS() %>" 
	      						onChange="checkFieldChange('<%=msgInst.getE02REQINS()%>',document.forms[0].E02REQINS.value)"
	      						onselect="checkFieldChange('<%=msgInst.getE02REQINS()%>',document.forms[0].E02REQINS.value)">
	      				<input type="text" name="E02REQINN" size="35" maxlength="35" value="<%= msgInst.getE02REQINN() %>" readonly >
      	    			<a href="javascript:GetCodeDescCNOFC('E02REQINS','E02REQINN','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
		  			</td>
     			</tr>
     			<tr id=trclear> 
		  			<td> 
		     			<div align="right">Moneda : </div>        
		  			</td>
      	  			<td nowrap> 
      	    			<input type="text" name="E02REQCCY" size="5" maxlength="3" value="<%= msgInst.getE02REQCCY() %>" 
      	    				onChange="checkFieldChange('<%=msgInst.getE02REQCCY()%>',document.forms[0].E02REQCCY.value)" 
      	    				onselect="checkFieldChange('<%=msgInst.getE02REQCCY()%>',document.forms[0].E02REQCCY.value)" >
      	    			<a href="javascript:GetInstCurrency('E02REQCCY',document.forms[0].E02REQBNK.value,document.forms[0].E02REQINS.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
      	  			</td>     
      			</tr>
      			<tr id=trdark>
          			<td nowrap>
              			<div align="right">Cliente : </div>
          			</td>
          			<td nowrap>
             			<input type="text" name="E02REQCUS" size="10" maxlength="9" value="<%= msgInst.getE02REQCUS() %>" 
              				onChange="checkFieldChange('<%=msgInst.getE02REQCUS()%>',document.forms[0].E02REQCUS.value)"
              				onselect="checkFieldChange('<%=msgInst.getE02REQCUS()%>',document.forms[0].E02REQCUS.value)">
             			<input type="text" name="E02REQCUN" size="35" maxlength="35" value="<%= msgInst.getE02REQCUN() %>" readonly > 
			 			<a href="javascript:GetCustomerDescId('E02REQCUS','E02REQCUN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
			 			<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
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
						<div align="right">Motivo de Operacion:</div>
				</td>
					<td nowrap width="60%">
						<input type="text" name="E02REQOPR" size="5" maxlength="4" value="<%= msgInst.getE02REQOPR() %>" 
							onChange="checkFieldChange('<%=msgInst.getE02REQOPR()%>',document.forms[0].E02REQOPR.value)"
							onselect="checkFieldChange('<%=msgInst.getE02REQOPR()%>',document.forms[0].E02REQOPR.value)"> 
						<input type="text"  name="E02REQORN" size="35" maxlength="35" value="<%= msgInst.getE02REQORN() %>" readonly>
						<a href="javascript:GetCodeDescCNOFC('E02REQOPR','E02REQORN',document.forms[0].E02REQOPC.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
						<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">
					</td>
				</tr>
				<tr>
					<td nowrap width="40%">
						<div align="right">Corresponsal:</div>
					</td>
					<td nowrap width="60%">
						<input type="text" name="E02REQCOR" size="10" maxlength="9" value="<%= msgInst.getE02REQCOR() %>" 
							onChange="checkFieldChange('<%=msgInst.getE02REQCOR()%>',document.forms[0].E02REQCOR.value)"
							onselect="checkFieldChange('<%=msgInst.getE02REQCOR()%>',document.forms[0].E02REQCOR.value)"> 
	      				<input type="text" name="E02REQCON" readonly size="35" maxlength="35" value="<%= msgInst.getE02REQCON() %>" readonly>
						<a href="javascript:GetCorrespondentDescId('E02REQCOR','E02REQCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
					</td>
				</tr>
				<tr>
					<td nowrap width="40%">
						<div align="right">AAD:</div>
					</td>
					<td nowrap width="60%">
						<input type="text" name="E02REQAAD" size="12" maxlength="10" value="<%= msgInst.getE02REQAAD() %>" 
							onChange="checkFieldChange('<%=msgInst.getE02REQAAD()%>',document.forms[0].E02REQAAD.value)"
							onselect="checkFieldChange('<%=msgInst.getE02REQAAD()%>',document.forms[0].E02REQAAD.value)"> 
					</td>
				</tr>
				<tr>
					<td nowrap width="40%">
						<div align="right">ALD:</div>
					</td>
					<td nowrap width="60%">
						<input type="text" name="E02REQALD" size="12" maxlength="10" value="<%= msgInst.getE02REQALD() %>" 
							onChange="checkFieldChange('<%=msgInst.getE02REQALD()%>',document.forms[0].E02REQALD.value)"
							onselect="checkFieldChange('<%=msgInst.getE02REQALD()%>',document.forms[0].E02REQALD.value)"> 
					</td>
				</tr>
				<tr>
					<td nowrap width="40%">
						<div align="right">Fecha de Liquidacion SPOT:</div>
					</td>
					<td nowrap width="60%">
                		<input type="text" name="E02REQVD1" size="2" maxlength="2" value="<%= msgInst.getE02REQVD1() %>">
                		<input type="text" name="E02REQVD2" size="2" maxlength="2" value="<%= msgInst.getE02REQVD2() %>">
                		<input type="text" name="E02REQVD3" size="2" maxlength="2" value="<%= msgInst.getE02REQVD3() %>">
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
						<input type="text" name="E02REQFEA" size="15" maxlength="15" value="<%= msgInst.getE02REQFEA() %>" onchange="checkFieldChange('<%=msgInst.getE02REQFEA()%>',document.forms[0].E02REQFEA.value)" 
						onselect="checkFieldChange('<%=msgInst.getE02REQFEA()%>',document.forms[0].E02REQFEA.value)">
						<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
					</td>
				</tr>
				<tr>
					<td nowrap width="40%">
						<div align="right">Valor Tasa de Cambio:</div>
					</td>
					<td nowrap width="60%">
						<input type="text" name="E02REQEXR" size="20" maxlength="15" value="<%= msgInst.getE02REQEXR() %>" onChange="checkFieldChange('<%=msgInst.getE02REQEXR()%>',document.forms[0].E02REQEXR.value)" 
						onselect="checkFieldChange('<%=msgInst.getE02REQEXR()%>',document.forms[0].E02REQEXR.value)"> 
					</td>
				</tr>
				<tr>
					<td nowrap width="40%">
						<div align="right">Valor en Moneda Local:</div>
					</td>
					<td nowrap width="60%">
						<input type="text" name="E02REQLCA" readonly size="20" maxlength="15" value="<%= msgInst.getE02REQLCA() %>" readonly> 
					</td>
				</tr>
				<tr>
					<td nowrap width="40%">
						<div align="right">Valor de Comision:</div>
					</td>
					<td nowrap width="60%">
						<input type="text" name="E02REQCOA" readonly size="20" maxlength="15" value="<%= msgInst.getE02REQCOA() %>" readonly>
						<%if (!msgInst.getE02REQCOA().equals("0.00")) {%>
						<a href="javascript:GetComission(document.forms[0].E02REQREF.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
						<%}%> 
					</td>
				</tr>
				<tr>
					<td nowrap>
						<div align="right">Valor Total:</div>
					</td>
					<td nowrap>
						<input type="text" name="E02REQTOA" size="20" maxlength="15" value="<%= msgInst.getE02REQTOA() %>" readonly > 
					</td>
				</tr>
    		 </table>
		</td>
	</tr>
</table>
<br>
 <TABLE  id="mainTable" class="tableinfo">
  	<TR>
  		<TD>
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
          					<input type="text" name="E02OFFOP1" size="3" maxlength="2" value="<%= msgInst.getField("E02OFFOP1").getString().trim() %>" readonly>
          					<input type="hidden" name="E02OFFGL1" value="<%= msgInst.getField("E02OFFGL1").getString().trim() %>">
          					<input type="text" name="E02OFFCO1" size="25" maxlength="25" readonly value="<%= msgInst.getField("E02OFFCO1").getString().trim() %>"
          					oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL1','E02OFFOP1','34'); return false;">
          				</div>
      				</td>
      				<td nowrap > 
          				<div align="center"> 
            				 <input type="text" name="E02OFFBK1" size="2" maxlength="2" value="<%= msgInst.getE02OFFBK1() %>">
          				</div>
      				</td>
      				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFBR1" size="3" maxlength="3" value="<%= msgInst.getE02OFFBR1() %>"
            				 oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
         				</div>
       				</td>
       				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFCY1" size="3" maxlength="3" value="<%= msgInst.getE02OFFCY1() %>"
             				oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFAC1" size="13" maxlength="12"  value="<%= msgInst.getE02OFFAC1() %>"
            				oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RX'); return false;">
          				</div>
       				</td>
       				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFVA1" size="18" maxlength="15" value="<%= msgInst.getE02OFFVA1() %>" onkeypress="enterDecimal()">
          				</div>
       				</td>
      			</tr>
      			<tr id="trdark" align="center"> 
      				<td nowrap > 
          				<div align="center" nowrap> 
          					<input type="text" name="E02OFFOP2" size="3" maxlength="2" value="<%= msgInst.getField("E02OFFOP2").getString().trim() %>" readonly>
          					<input type="hidden" name="E02OFFGL2" value="<%= msgInst.getField("E02OFFGL2").getString().trim() %>">
          					<input type="text" name="E02OFFCO2" size="25" maxlength="25" readonly value="<%= msgInst.getField("E02OFFCO2").getString().trim() %>"
          					oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL2','E02OFFOP2','34'); return false;">
          				</div>
      				</td>
      				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFBK2" size="2" maxlength="2" value="<%= msgInst.getE02OFFBK2() %>">
          				</div>
     				 </td>
      				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFBR2" size="3" maxlength="3" value="<%= msgInst.getE02OFFBR2() %>"
             				oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFCY2" size="3" maxlength="3" value="<%= msgInst.getE02OFFCY2() %>"
             				oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E01REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap > 
         				 <div align="center"> 
             				<input type="text" name="E02OFFAC2" size="13" maxlength="12"  value="<%= msgInst.getE02OFFAC2() %>"
             				oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RX'); return false;">
          				</div>
        			</td>
        			<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFVA2" size="18" maxlength="15" value="<%= msgInst.getE02OFFVA2() %>" onkeypress="enterDecimal()">
          				</div>
        			</td>
      			</tr>
      			<tr id="trclear" align="center"> 
      				<td nowrap > 
         				<div align="center" nowrap> 
          					<input type="text" name="E02OFFOP3" size="3" maxlength="2" value="<%= msgInst.getField("E02OFFOP3").getString().trim() %>" readonly>
          					<input type="hidden" name="E02OFFGL3" value="<%= msgInst.getField("E02OFFGL3").getString().trim() %>">
          					<input type="text" name="E02OFFCO3" size="25" maxlength="25" readonly value="<%= msgInst.getField("E02OFFCO3").getString().trim() %>"
          					oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL3','E02OFFOP3','34'); return false;">
          				</div>
      				</td>
      				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFBK3" size="2" maxlength="2" value="<%= msgInst.getE02OFFBK3() %>">
          				</div>
      				</td>
      				<td nowrap> 
          				<div align="center"> 
            				<input type="text" name="E02OFFBR3" size="3" maxlength="3" value="<%= msgInst.getE02OFFBR3() %>"
             				oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFCY3" size="3" maxlength="3" value="<%= msgInst.getE02OFFCY3() %>"
             				oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFAC3" size="13" maxlength="12"  value="<%= msgInst.getE02OFFAC3() %>"
             				oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RX'); return false;">
          				</div>
       				</td>
       				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFVA3" size="18" maxlength="15" value="<%= msgInst.getE02OFFVA3() %>" onkeypress="enterDecimal()">
          				</div>
       				</td>
      			</tr>
      			<tr id="trdark" align="center"> 
      				<td nowrap > 
          				<div align="center" nowrap> 
          					<input type="text" name="E02OFFOP4" size="3" maxlength="2" value="<%= msgInst.getField("E02OFFOP4").getString().trim() %>" readonly>
          					<input type="hidden" name="E02OFFGL4" value="<%= msgInst.getField("E02OFFGL4").getString().trim() %>">
          					<input type="text" name="E02OFFCO4" size="25" maxlength="25" readonly value="<%= msgInst.getField("E02OFFCO4").getString().trim() %>"
         					 oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL4','E02OFFOP4','34'); return false;">
          				</div>
      				</td>
      				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFBK4" size="2" maxlength="2" value="<%= msgInst.getE02OFFBK4() %>">
          				</div>
      				</td>
      				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFBR4" size="3" maxlength="3" value="<%= msgInst.getE02OFFBR4() %>"
            				oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFCY4" size="3" maxlength="3" value="<%= msgInst.getE02OFFCY4() %>"
             				oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFAC4" size="13" maxlength="12"  value="<%= msgInst.getE02OFFAC4() %>"
             				oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RX'); return false;">
          				</div>
        			</td>
        			<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFVA4" size="18" maxlength="15" value="<%= msgInst.getE02OFFVA4() %>" onkeypress="enterDecimal()">
          				</div>
        			</td>
      			</tr>
      			<tr id="trclear" align="center"> 
      				<td nowrap > 
          				<div align="center" nowrap> 
          					<input type="text" name="E02OFFOP5" size="3" maxlength="2" value="<%= msgInst.getField("E02OFFOP5").getString().trim() %>" readonly>
          					<input type="hidden" name="E02OFFGL5" value="<%= msgInst.getField("E02OFFGL5").getString().trim() %>">
          					<input type="text" name="E02OFFCO5" size="25" maxlength="25" readonly value="<%= msgInst.getField("E02OFFCO5").getString().trim() %>"
          					oncontextmenu="showPopUp(conceptHelp,this.name,document.forms[0].E02REQBNK.value,'','E02OFFGL5','E02OFFOP5','34'); return false;">
          				</div>
      				</td>
      				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFBK5" size="2" maxlength="2" value="<%= msgInst.getE02OFFBK5() %>">
          				</div>
      				</td>
      				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFBR5" size="3" maxlength="3" value="<%= msgInst.getE02OFFBR5() %>"
             				oncontextmenu="showPopUp(branchHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap> 
          				<div align="center"> 
             				<input type="text" name="E02OFFCY5" size="3" maxlength="3" value="<%= msgInst.getE02OFFCY5() %>"
             				oncontextmenu="showPopUp(currencyHelp,this.name,document.forms[0].E02REQBNK.value,'','','',''); return false;">
          				</div>
       				</td>
       				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFAC5" size="13" maxlength="12"  value="<%= msgInst.getE02OFFAC5() %>"
             				oncontextmenu="showPopUp(lnreferHelp,this.name,document.forms[0].E02REQBNK.value,'',document.forms[0].E02REQCUS.value,'','RX'); return false;">
          				</div>
       				</td>
       				<td nowrap > 
          				<div align="center"> 
             				<input type="text" name="E02OFFVA5" size="18" maxlength="15" value="<%= msgInst.getE02OFFVA5() %>" onkeypress="enterDecimal()">
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
//	  document.forms[0].E02REQOPC.focus();
  </script>
  
</form>
</body>
</html>
