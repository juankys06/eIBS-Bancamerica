<html> 
<head>
<title>Consulta de Operaciones Compraventa Moneda Extranjera</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EPR039501Message"  scope="session" />

<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E01REQBRN.value == "") document.forms[0].E01REQBRN.value = "999";
   }
</script>
</head>
<body>

<H3 align="center">Consulta de Operaciones Compraventa Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="foreign_currency_exchange_mov_inq,EPR0395"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.forex.JSEPR0395" onsubmit="checkBranch()">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     	<tr id=trdark> 
	      <td nowrap width="40%"> 
	        <div align="right">Banco : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01REQBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01REQBNK()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01REQBRN" size="4" maxlength="3" value="">
	        <a href="javascript:GetBranch('E01REQBRN',document.forms[0].E01REQBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Cliente : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REQCUS" size="10" maxlength="9" value="<%= msgMT.getE01REQCUS()%>">
            <a href="javascript:GetCustomerDescId('E01REQCUS','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Numero de Referencia : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01REQREF" size="15" maxlength="12" onKeypress="enterInteger()" value="<%= msgMT.getE01REQREF()%>">
	      </td>
     	</tr>
      	<tr id=trdark>
          <td nowrap>
              <div align="right">Fecha Creacion - Desde : </div>
          </td>
          <td nowrap> 
              <input type="text" name="E01REQCD1" size="3" maxlength="2" value="<%= msgMT.getE01REQCD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQCD2" size="3" maxlength="2" value="<%= msgMT.getE01REQCD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQCD3" size="3" maxlength="2" value="<%= msgMT.getE01REQCD3()%>" onkeypress="enterInteger()">
              &nbsp; Hasta : &nbsp; 
              <input type="text" name="E01REQCH1" size="3" maxlength="2" value="<%= msgMT.getE01REQCH1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQCH2" size="3" maxlength="2" value="<%= msgMT.getE01REQCH2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REQCH3" size="3" maxlength="2" value="<%= msgMT.getE01REQCH3()%>" onkeypress="enterInteger()">               
          </td>
        </tr>
      	<tr id=trclear> 
		  <td> 
		     <div align="right">Instrumento : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REQINS" size="5" maxlength="4" value="<%= msgMT.getE01REQINS()%>">
      	    <a href="javascript:GetCodeCNOFC('E01REQINS','YJ')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trdark> 
	      <td nowrap> 
	        <div align="right">Operacion : </div>
	      </td>
	      <td nowrap>
	      	<select name="E01REQOPC">
	      		<option value="" selected<%if (msgMT.getE01REQOPC().equals("")) { out.print("selected"); }%>></option>
          	  	<option value="P" <%if (msgMT.getE01REQOPC().equals("P")) { out.print("selected"); }%>>Compra</option>
                <option value="S" <%if (msgMT.getE01REQOPC().equals("S")) { out.print("selected"); }%>>Venta</option>
                <option value="C" <%if (msgMT.getE01REQOPC().equals("C")) { out.print("selected"); }%>>Canje</option>
                <option value="F" <%if (msgMT.getE01REQOPC().equals("F")) { out.print("selected"); }%>>Fraccionamiento</option>
            </select>
		  </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap> 
	        <div align="right">Estado : </div>
	      </td>
	      <td nowrap>
	      	<select name="E01REQSTS">
	      		<option value="" selected<%if (msgMT.getE01REQSTS().equals("")) { out.print("selected"); }%>></option>
          	  	<option value="P" <%if (msgMT.getE01REQSTS().equals("P")) { out.print("selected"); }%>>Pendiente</option>
                <option value="E" <%if (msgMT.getE01REQSTS().equals("E")) { out.print("selected"); }%>>Extracupo</option>
                <option value="A" <%if (msgMT.getE01REQSTS().equals("A")) { out.print("selected"); }%>>Extracupo Aprobado</option>
                <option value="R" <%if (msgMT.getE01REQSTS().equals("R")) { out.print("selected"); }%>>Rechazado</option>
                <option value="X" <%if (msgMT.getE01REQSTS().equals("X")) { out.print("selected"); }%>>Liquidado</option>
                <option value="V" <%if (msgMT.getE01REQSTS().equals("V")) { out.print("selected"); }%>>Reversado</option>
                <option value="T" <%if (msgMT.getE01REQSTS().equals("T")) { out.print("selected"); }%>>Canjeado</option>
                <option value="F" <%if (msgMT.getE01REQSTS().equals("F")) { out.print("selected"); }%>>Fraccionado</option>
                <option value="I" <%if (msgMT.getE01REQSTS().equals("I")) { out.print("selected"); }%>>Ingresado</option>
                <option value="C" <%if (msgMT.getE01REQSTS().equals("C")) { out.print("selected"); }%>>Liquidado Aprobado</option>
            </select>
		  </td>
     	</tr>
     </table>
    </td>
   </tr>
  </table>
  
  <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </p>
  
	<script language="JavaScript">
	  document.forms[0].E01REQBNK.focus();
	  document.forms[0].E01REQBNK.select();
	</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
