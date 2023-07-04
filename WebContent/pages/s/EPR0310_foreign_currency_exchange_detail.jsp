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
datapro.eibs.beans.EPR031001Message msgInst = (datapro.eibs.beans.EPR031001Message) mtList.getRecord();

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

</head>
<body>

<H3 align="center">Mantenimiento de Oficinas de Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_detail, EPR0310"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0310">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="ROW" VALUE="<%= row %>">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01FETBNK" readonly size="3" maxlength="2" value="<%= msgInst.getE01FETBNK() %>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01FETBRN" readonly size="5" maxlength="3" value="<%= msgInst.getE01FETBRN() %>">
	      	<input type="text" name="E01FETBRM" readonly size="35" maxlength="35" readonly value="<%= msgInst.getE01FETBRM() %>">
		  </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01FETCCY" readonly size="5" maxlength="3" value="<%= msgInst.getE01FETCCY() %>">
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
				<div align="right">Codigo de Moneda de Limites :</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01FETLIC" size="5" maxlength="3" value="<%= msgInst.getE01FETLIC() %>" readonly> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Fecha/Hora Ultima Actualizacion :</div>
			</td>
			<td nowrap width="60%">
				<%= Util.formatDate(msgInst.getE01FETAD1(),msgInst.getE01FETAD2(),msgInst.getE01FETAD3())  + " - " + Util.formatTime(msgInst.getE01FETACT()) %>
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Usuario Ultima Actualizacion :</div>
			</td>
			<td nowrap width="60%">
				<%= msgInst.getE01FETACU() %>
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
			<td width="33%"></td>
			<td width="33%" align="center"><B>Compra</B></td>
			<td width="33%" align="center"><B>Venta</B></td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Dia</td>
			<td align="center">
				<input type="text" name="E01FETPMD"	value="<%= msgInst.getE01FETPMD() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSMD"	value="<%= msgInst.getE01FETSMD() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Mes</td>
			<td align="center">
				<input type="text" name="E01FETPMM"	value="<%= msgInst.getE01FETPMM() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSMM"	value="<%= msgInst.getE01FETSMM() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Semestre</td>
			<td align="center">
				<input type="text" name="E01FETPMS"	value="<%= msgInst.getE01FETPMS() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSMS"	value="<%= msgInst.getE01FETSMS() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Limite Maximo por Año</td>
			<td align="center">
				<input type="text" name="E01FETPMY"	value="<%= msgInst.getE01FETPMY() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSMY"	value="<%= msgInst.getE01FETSMY() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Estado</td>
			<td align="center">
				<%if (msgInst.getE01FETPST().equals("0")) {%> Cerrado <%}%>
				<%if (msgInst.getE01FETPST().equals("1")) {%> Abierto <%}%>
			</td>
			<td align="center">
				<%if (msgInst.getE01FETSST().equals("0")) {%> Cerrado <%}%>
				<%if (msgInst.getE01FETSST().equals("1")) {%> Abierto <%}%>
			</td>
		</tr>
		<tr>
			<td align="right">Acumulado por Dia</td>
			<td align="center">
				<input type="text" name="E01FETPCD"	value="<%= msgInst.getE01FETPCD() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSCD"	value="<%= msgInst.getE01FETSCD() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Acumulado por Mes</td>
			<td align="center">
				<input type="text" name="E01FETPCM"	value="<%= msgInst.getE01FETPCM() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSCM"	value="<%= msgInst.getE01FETSCM() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Acumulado por Semestre</td>
			<td align="center">
				<input type="text" name="E01FETPCS"	value="<%= msgInst.getE01FETPCS() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSCS"	value="<%= msgInst.getE01FETSCS() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Acumulado por Año</td>
			<td align="center">
				<input type="text" name="E01FETPCY"	value="<%= msgInst.getE01FETPCY() %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSCY"	value="<%= msgInst.getE01FETSCY() %>" readonly>
			</td>
		</tr>
		<tr>
			<td align="right">Fecha Ultima</td>
			<td align="center">
				<input type="text" name="E01FETPD1"	value="<%= Util.formatDate(msgInst.getE01FETPD1(),msgInst.getE01FETPD2(),msgInst.getE01FETPD3()) %>" readonly>
			</td>
			<td align="center">
				<input type="text" name="E01FETSD1"	value="<%= Util.formatDate(msgInst.getE01FETSD1(),msgInst.getE01FETSD2(),msgInst.getE01FETSD3()) %>" readonly>
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>

</form>
</body>
</html>
