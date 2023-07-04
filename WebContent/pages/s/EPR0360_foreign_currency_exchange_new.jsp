<html>
<head> 
<title>Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<%@ page import ="datapro.eibs.master.Util" %>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "msgInst" 	class= "datapro.eibs.beans.EPR036001Message"  	scope="session" />
<jsp:useBean id= "error" 	class= "datapro.eibs.beans.ELEERRMessage"  		scope="session" />
<jsp:useBean id= "userPO" 	class= "datapro.eibs.beans.UserPos"  			scope="session"/>

<%
int NEW = 0;
try { NEW = Integer.parseInt(request.getParameter("NEW"));} catch (Exception e) {}
if (NEW == 1) {
msgInst.destroy();
}
 
if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
%>
<SCRIPT Language="Javascript">
        showErrors();
</SCRIPT>
<%}%>

<script language="Javascript1.1">
function goAction(opt) {

	if (opt == "S") {
      ok = confirm("Está seguro que desea ingresar cheque con asientos contables a cuentas de orden?");
	  if (ok) {
	  	document.forms[0].H01FLGWK1.value = opt;
	  	document.forms[0].submit();
	  }
	}
	if (opt == "V") {  
		document.forms[0].H01FLGWK1.value = opt;
		document.forms[0].submit();
	}
}
</script>

</head>
<body>

<H3 align="center">Ingreso Compras de Cheques de Remesa y Cobranza<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_new, EPR0360"></H3>
<hr size="4">


<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0360">
 
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="400">
  <INPUT TYPE=HIDDEN NAME="H01FLGWK1" VALUE="V">
  
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap width="60%"> 
	        <input type="text" name="E01REQBNK" size="3" maxlength="2" value="<%= userPO.getBank() %>" readonly>
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REQBRN" size="5" maxlength="3" value="<%= userPO.getBranch() %>" readonly>
		  </td>
     	</tr>
     	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Instrumento : </div>
	      </td>
	      <td nowrap>
	      	<input type="text" name="E01REQINS" size="5" maxlength="4" value="<%= msgInst.getE01REQINS() %>">
	      	<input type="text" name="E01REQINN" size="35" maxlength="35" value="<%= msgInst.getE01REQINN() %>" readonly >
      	    <a href="javascript:GetCodeDescCNOFC('E01REQINS','E01REQINN','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
		  </td>
     	</tr>
     	<tr id=trclear> 
		  <td> 
		     <div align="right">Moneda : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REQCCY" size="5" maxlength="3" value="<%= msgInst.getE01REQCCY() %>">
      	    <a href="javascript:GetInstCurrency('E01REQCCY',document.forms[0].E01REQBNK.value,document.forms[0].E01REQINS.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
      	  </td>     
      	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Cliente : </div>
          </td>
          <td nowrap>
             <input type="text" name="E01REQCUS" size="10" maxlength="9" value="<%= msgInst.getE01REQCUS() %>">
             <input type="text" name="E01REQCUN" size="35" maxlength="35" value="<%= msgInst.getE01REQCUN() %>" readonly > 
			 <a href="javascript:GetCustomerDescId('E01REQCUS','E01REQCUN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
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
				<div align="right">Numero del Cheque:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCHK" size="10" maxlength="9" value="<%= msgInst.getE01REQCHK() %>"> 
      	    	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">  
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Banco del Cheque:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCHB" size="5" maxlength="4" value="<%= msgInst.getE01REQCHB() %>">
				<input type="text" name="E01REQCHN" size="35" maxlength="35" value="<%= msgInst.getE01REQCHN() %>" readonly >
      	    	<a href="javascript:GetCodeDescCNOFC('E01REQCHB','E01REQCHN','YE')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">  
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Valor en Moneda Extranjera:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQFEA" size="20" maxlength="15" value="<%= msgInst.getE01REQFEA() %>" onkeypress="enterDecimal()">
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20">  
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Motivo de Operacion:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQOPR" size="5" maxlength="4" value="<%= msgInst.getE01REQOPR() %>"> 
				<input type="text" name="E01REQORN" size="35" maxlength="35" value="<%= msgInst.getE01REQORN() %>" readonly> 
      	    	<a href="javascript:GetCodeDescCNOFC('E01REQOPR','E01REQORN','Y*')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	    	<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
			</td>
		</tr>
		<tr>
			<td nowrap width="40%">
				<div align="right">Corresponsal:</div>
			</td>
			<td nowrap width="60%">
				<input type="text" name="E01REQCOR" size="10" maxlength="9" value="<%= msgInst.getE01REQCOR() %>"> 
	      		<input type="text" name="E01REQCON" readonly size="35" maxlength="35" value="<%= msgInst.getE01REQCON() %>" readonly>
				<a href="javascript:GetCorrespondentDescId('E01REQCOR','E01REQCON','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
				<img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" align="absbottom" border="0" width="16" height="20"> 
			</td>
		</tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
  
  <p align="center"> 
    <input id="EIBSBTN" type="button" name="Submit" value="Validar" onclick="goAction('V')">
    <input id="EIBSBTN" type="button" name="Submit" value="Salvar" onclick="goAction('S')">
    <input id="EIBSBTN" type="button" name="Submit" value="Cancelar" onclick="top.close()">
  </p>
</form>
</body>
</html>
