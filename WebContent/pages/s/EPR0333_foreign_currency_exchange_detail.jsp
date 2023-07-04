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

<% 
int row = 0;
try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
mtList.setCurrentRow(row);
datapro.eibs.beans.EPR033301Message msgInst = (datapro.eibs.beans.EPR033301Message) mtList.getRecord();

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

</head>
<body>

<H3 align="center">Consulta de Solicitudes de Canje de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_detail, EPR0333"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0333">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Referencia Original: </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01REQROR" readonly size="15" maxlength="15" value="<%= msgInst.getE01REQROR() %>">
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
	      	<input type="text" name="E01REQINO" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQINO() %>">
	      	<input type="text" name="E01REQINP" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01REQINP() %>">
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
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Extranjera:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQFEA" size="15" maxlength="15" value="<%= msgInst.getE01REQFEA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Tasa de Cambio:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQEXR" size="15" maxlength="12" value="<%= msgInst.getE01REQEXR() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Local:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQLCA" size="15" maxlength="15" value="<%= msgInst.getE01REQLCA() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor de Comision:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCOA" size="15" maxlength="15" value="<%= msgInst.getE01REQCOA() %>" readonly>
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor Total:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQTOA" size="15" maxlength="15" value="<%= msgInst.getE01REQTOA() %>" readonly> 
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
          <div align="center" nowrap> 
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
             <input type="text" name="E01OFFVA1" size="18" maxlength="15"  value="<%= msgInst.getE01OFFVA1() %>" readonly>
          </div>
       </td>
      </tr>
      <%}%>
      <%if (!msgInst.getE01OFFVA2().equals("0.00")) {%> 
      <tr id="trdark" align="center"> 
      <td nowrap > 
          <div align="center" nowrap> 
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
          <div align="center" nowrap> 
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
          <div align="center" nowrap> 
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
          <div align="center" nowrap> 
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
    <table id="headTable" width="100%">
    	<tr id="trdark" align="center"> 
      	<td nowrap align="center" >Codigo Denominacion</td>
      	<td nowrap align="center" >Nombre Denominacion</td>
      	<td nowrap align="center" >Beneficiario</td>
      	<td nowrap align="center" >Numero Inicial</td>
      	<td nowrap align="center" >Cantidad</td>
    	</tr>
    	<%if (!msgInst.getE01REQCQ1().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E01REQCS1" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQCS1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCN1" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQCN1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQBE1" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQBE1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCI1" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCI1() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCQ1" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCQ1() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE01REQCQ2().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E01REQCS2" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQCS2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCN2" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQCN2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQBE2" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQBE2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCI2" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCI2() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCQ2" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCQ2() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE01REQCQ3().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E01REQCS3" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQCS3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCN3" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQCN3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQBE3" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQBE3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCI3" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCI3() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCQ3" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCQ3() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE01REQCQ4().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E01REQCS4" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQCS4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCN4" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQCN4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQBE4" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQBE4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCI4" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCI4() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCQ4" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCQ4() %>">
	      	</td>
		</tr>
		<%}%>
		<%if (!msgInst.getE01REQCQ5().equals("0")) {%>     
		<tr>
			<td nowrap align="center"> 
	        	<input type="text" name="E01REQCS5" readonly size="5" maxlength="4" value="<%= msgInst.getE01REQCS5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCN5" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQCN5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQBE5" readonly size="40" maxlength="35" value="<%= msgInst.getE01REQBE5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCI5" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCI5() %>">
	      	</td>
	      	<td nowrap align="center"> 
	        	<input type="text" name="E01REQCQ5" readonly size="10" maxlength="10" value="<%= msgInst.getE01REQCQ5() %>">
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
			<td nowrap width="40%">
				<div align="right">Motivo de Operacion:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQORN" size="35" maxlength="35" value="<%= msgInst.getE01REQORN() %>" readonly> 
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
				<div align="right">Fecha/Hora de Liquidacion:</div>
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
				<div align="right">Usuario que liquida:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCRU" size="12" maxlength="10" value="<%= msgInst.getE01REQCRU() %>" readonly> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
</form>
</body>
</html>
