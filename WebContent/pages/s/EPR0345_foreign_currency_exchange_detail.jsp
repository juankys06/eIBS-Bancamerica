<html> 
<head>
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "mtList" 	class= "datapro.eibs.beans.JBObjList"  		scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  	scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  		scope="session"/>
<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR034501Message"  		scope="session"/>

<% 
//int row = 0;
//try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
//mtList.setCurrentRow(row);
//datapro.eibs.beans.EPR034501Message msgInst = (datapro.eibs.beans.EPR034501Message) mtList.getRecord();

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

<script language="Javascript1.1">
function goAction(opt) {
	document.forms[0].H02FLGWK1.value = opt;
	document.forms[0].submit();
}
</script>

</head>
<body>

<H3 align="center">Aprobacion/Rechazo de Compraventa de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_detail, EPR0345"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0345">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK1">
  
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
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Operacion : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REQOPN" readonly size="18" maxlength="15" value="<%= msgInst.getE01REQOPN() %>">
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
	      	<input type="text" name="E01REQINN" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01REQINN() %>">
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REQCCY" readonly size="5" maxlength="3" value="<%= msgInst.getE01REQCCY() %>">
	      	<input type="text" name="E01REQCCN" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01REQCCN() %>">
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01REQCUS" readonly size="10" maxlength="9" value="<%= msgInst.getE01REQCUS() %>">
              <input type="text" name="E01REQCUN" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01REQCUN() %>"> 
          </td>
        </tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <%if (!msgInst.getE01REQROR().equals("0")) {%>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%"> 
	        <div align="right">Referencia Original: </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01REQROR" readonly size="15" maxlength="15" value="<%= msgInst.getE01REQROR() %>">
	      </td>
		</tr>
		<tr>
			<td nowrap> 
	        <div align="right">Instrumento Original: </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REQINO" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQINO() %>">
	      	<input type="text" name="E01REQINP" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01REQINP() %>">
		  </td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Extranjera Original:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQFEP" size="20" maxlength="15" value="<%= msgInst.getE01REQFEP() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Local Original:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQLCP" size="20" maxlength="15" value="<%= msgInst.getE01REQLCP() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor de Comision Original:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCOP" size="20" maxlength="15" value="<%= msgInst.getE01REQCOP() %>" readonly>
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Total Original:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQTOP" size="20" maxlength="15" value="<%= msgInst.getE01REQTOP() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  <%}%>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Extranjera:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQFEA" size="20" maxlength="15" value="<%= msgInst.getE01REQFEA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Tasa de Cambio:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQEXR" size="20" maxlength="15" value="<%= msgInst.getE01REQEXR() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Local:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQLCA" size="20" maxlength="15" value="<%= msgInst.getE01REQLCA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor de Comision:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCOA" size="20" maxlength="15" value="<%= msgInst.getE01REQCOA() %>" readonly>
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
				<input type="text" name="E01REQTOA" size="20" maxlength="15" value="<%= msgInst.getE01REQTOA() %>" readonly> 
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
				<input type="text" name="E01REQORN" size="35" maxlength="35" value="<%= msgInst.getE01REQORN() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Corresponsal:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCOR" size="10" maxlength="9" value="<%= msgInst.getE01REQCOR() %>" readonly> 
	      		<input type="text" name="E01REQCON" size="35" maxlength="35" value="<%= msgInst.getE01REQCON() %>" readonly>
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">AAD:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQAAD" size="12" maxlength="10" value="<%= msgInst.getE01REQAAD() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">ALD:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQALD" size="12" maxlength="10" value="<%= msgInst.getE01REQALD() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Fecha de Liquidacion SPOT:</div>
			</td>
			<td nowrap width="60%">
                <input type="text" name="E01REQVD1" size="2" maxlength="2" value="<%= msgInst.getE01REQVD1() %>" readonly>
                <input type="text" name="E01REQVD2" size="2" maxlength="2" value="<%= msgInst.getE01REQVD2() %>" readonly>
                <input type="text" name="E01REQVD3" size="2" maxlength="2" value="<%= msgInst.getE01REQVD3() %>" readonly>
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Fecha/Hora de Creacion:</div>
			</td>
			<td nowrap width="60%">
                <input type="text" name="E01REQCD1" size="2" maxlength="2" value="<%= msgInst.getE01REQCD1() %>" readonly>
                <input type="text" name="E01REQCD2" size="2" maxlength="2" value="<%= msgInst.getE01REQCD2() %>" readonly>
                <input type="text" name="E01REQCD3" size="2" maxlength="2" value="<%= msgInst.getE01REQCD3() %>" readonly>
				<input type="text" name="E01REQCRT" size="8" maxlength="8" value="<%= Util.formatTime(msgInst.getE01REQCRT()) %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Usuario que creo:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCRU" size="12" maxlength="10" value="<%= msgInst.getE01REQCRU() %>" readonly> 
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
    <%if (!msgInst.getE01OFFVA1().equals("0.00")) {%>
    <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP1" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP1() %>" readonly>
          <input type="text" name="E01OFFCO1" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO1() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK1" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK1() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR1" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR1() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY1" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY1() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC1" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC1() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA1" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA1() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgInst.getE01OFFVA2().equals("0.00")) {%> 
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP2" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP2() %>" readonly>
          <input type="text" name="E01OFFCO2" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO2() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK2" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK2() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR2" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR2() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY2" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY2() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC2" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC2() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA2" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA2() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgInst.getE01OFFVA3().equals("0.00")) {%> 
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP3" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP3() %>" readonly>
          <input type="text" name="E01OFFCO3" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO3() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK3" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK3() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR3" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR3() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY3" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY3() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC3" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC3() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA3" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA3() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgInst.getE01OFFVA4().equals("0.00")) {%> 
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP4" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP4() %>" readonly>
          <input type="text" name="E01OFFCO4" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO4() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK4" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK4() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR4" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR4() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY4" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY4() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC4" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC4() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA4" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA4() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgInst.getE01OFFVA5().equals("0.00")) {%> 
      <tr id="trclear" align="center"> 
      <td nowrap > 
          <div align="center"> 
          <input type="text" name="E01OFFOP5" size="3" maxlength="2" value="<%= msgInst.getE01OFFOP5() %>" readonly>
          <input type="text" name="E01OFFCO5" size="25" maxlength="25" readonly value="<%= msgInst.getE01OFFCO5() %>">
          </div>
      </td>
      <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFBK5" size="2" maxlength="2" value="<%= msgInst.getE01OFFBK5() %>" readonly>
          </div>
      </td>
      <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFBR5" size="3" maxlength="3" value="<%= msgInst.getE01OFFBR5() %>" readonly>
          </div>
       </td>
       <td nowrap> 
          <div align="center"> 
             <input type="text" name="E01OFFCY5" size="3" maxlength="3" value="<%= msgInst.getE01OFFCY5() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFAC5" size="12" maxlength="12"  value="<%= msgInst.getE01OFFAC5() %>" readonly>
          </div>
       </td>
       <td nowrap > 
          <div align="center"> 
             <input type="text" name="E01OFFVA5" size="18" maxlength="15" value="<%= msgInst.getE01OFFVA5() %>" readonly>
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
  <br>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Motivo de Rechazo 1:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQRJ1" size="45" maxlength="40" value="<%= msgInst.getE01REQRJ1() %>"> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Motivo de Rechazo 2:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQRJ2" size="45" maxlength="40" value="<%= msgInst.getE01REQRJ2() %>"> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Aprobar"  onclick="goAction('A')">
    <input id="EIBSBTN" type="button" name="Submit" value="Rechazar" onclick="goAction('R')">
  </p>
</form>
</body>
</html>
