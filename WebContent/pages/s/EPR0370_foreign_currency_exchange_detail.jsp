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

<SCRIPT Language="Javascript">

function GetFXform()
{
	var ref = document.forms[0].E01REQREF.value;
	page = webapp + "/servlet/datapro.eibs.reports.JSEFRM000?SCREEN=6&E01SELREF=" + ref;
	CenterWindow(page,700,500,2);
}



builtNewMenu(fx_i_opt);

</SCRIPT>



<% 
int row = 0;
try { row = Integer.parseInt(request.getParameter("ROW"));} catch (Exception e) {}
mtList.setCurrentRow(row);
datapro.eibs.beans.EPR037001Message msgInst = (datapro.eibs.beans.EPR037001Message) mtList.getRecord();

if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}
out.println("<SCRIPT> initMenu(); </SCRIPT>");
%>

</head>
<body>

<h3 align="center">Compraventa Moneda Extranjera<BR>Liquidacion de Compra de Cheques de Remesa y Cobranza<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_detail, EPR0370"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0370">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  <INPUT TYPE=HIDDEN NAME="H02FLGWK1">
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
				<div align="right">Valor en Moneda Extranjera:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQFEA" size="20" maxlength="15" value="<%= msgInst.getE01REQFEA() %>" readonly> 
			</td>
		</tr>
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
  
</form>
</body>
</html>
