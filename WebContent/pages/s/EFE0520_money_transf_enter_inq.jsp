<html> 
<head>
<title>Consulta de Remesas</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "msgMT" class= "datapro.eibs.beans.EFE052001Message"  scope="session" />

<script language="Javascript1.1">
   function checkBranch() {
   		if (document.forms[0].E01REMBRN.value == "") document.forms[0].E01REMBRN.value = "999";
   }
</script>
</head>
<body>

<H3 align="center">Consulta de Remesas de Efectivo en Moneda Extranjera<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="money_transfer_enter_inq,EFE0520"></H3>
<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.moneytransfer.JSEFE0520" onsubmit="checkBranch()">
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
	        <input type="text" name="E01REMBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%= msgMT.getE01REMBNK()%>">
	      </td>
     	</tr>
     	<tr id=trclear> 
	      <td nowrap width="40%"> 
	        <div align="right">Oficina : </div>
	      </td>
	      <td nowrap> 
	        <input type="text" name="E01REMBRN" size="4" maxlength="3" value="">
	        <a href="javascript:GetBranch('E01REMBRN',document.forms[0].E01REMBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a> 
	      </td>
     	</tr>
     	<tr id=trdark> 
		  <td> 
		     <div align="right">Carrier : </div>        
		  </td>
      	  <td nowrap> 
      	    <input type="text" name="E01REMCAR" size="5" maxlength="4" value="<%= msgMT.getE01REMCAR()%>">
      	    <a href="javascript:GetCodeCNOFC('E01REMCAR','YK')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="bottom" border="0" ></a>
      	  </td>     
      	</tr>
      	<tr id=trclear>
          <td nowrap>
              <div align="right">Moneda : </div>
          </td>
          <td nowrap>
              <input type="text" name="E01REMCCY" size="4" maxlength="3" value="<%= msgMT.getE01REMCCY()%>">
              <a href="javascript:GetCurrency('E01REMCCY','')"><img src="<%=request.getContextPath()%>/images/1b.gif" align="bottom" border="0"></a> 
          </td>
        </tr>
        <tr id=trdark>          
          <td nowrap>
             <div align="right">Tipo de Remesa : </div>
          </td>
          <td nowrap>
             <select name="E01REMTYP">
             	<option value=" " ></option>
          	  	<option value="C" <%if (msgMT.getE01REMTYP().equals("C")) { out.print("selected"); }%>>Canje</option>
                <option value="A" <%if (msgMT.getE01REMTYP().equals("A")) { out.print("selected"); }%>>ATM</option>
                <option value="N" <%if (msgMT.getE01REMTYP().equals("N")) { out.print("selected"); }%>>Normal</option>
           	</select>
          </td>
        </tr>
        <tr id=trclear>
          <td nowrap>
             <div align="right">Status : </div>
          </td>
          <td nowrap>
             <select name="E01REMSTS" >
                <OPTION value=" " ></OPTION>
                <OPTION value="P" <% if (msgMT.getE01REMSTS().equals("P")) out.print("SELECTED");%>>Pendientes</OPTION>
                <OPTION value="A" <% if (msgMT.getE01REMSTS().equals("A")) out.print("SELECTED");%>>Aprobadas</OPTION>
                <OPTION value="R" <% if (msgMT.getE01REMSTS().equals("R")) out.print("SELECTED");%>>Rechazadas</OPTION>
                <OPTION value="S" <% if (msgMT.getE01REMSTS().equals("S")) out.print("SELECTED");%>>Enviadas</OPTION>
                <OPTION value="V" <% if (msgMT.getE01REMSTS().equals("V")) out.print("SELECTED");%>>Recibidas</OPTION>
            </select> 
          </td>
        </tr>
        <tr id=trdark>
          <td nowrap>
              <div align="right">Fecha Creacion - Desde : </div>
          </td>
          <td nowrap> 
              <input type="text" name="E01REMFD1" size="3" maxlength="2" value="<%= msgMT.getE01REMFD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REMFD2" size="3" maxlength="2" value="<%= msgMT.getE01REMFD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REMFD3" size="3" maxlength="2" value="<%= msgMT.getE01REMFD3()%>" onkeypress="enterInteger()">
              &nbsp; Hasta : &nbsp; 
              <input type="text" name="E01REMTD1" size="3" maxlength="2" value="<%= msgMT.getE01REMTD1()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REMTD2" size="3" maxlength="2" value="<%= msgMT.getE01REMTD2()%>" onkeypress="enterInteger()">
              <input type="text" name="E01REMTD3" size="3" maxlength="2" value="<%= msgMT.getE01REMTD3()%>" onkeypress="enterInteger()">               
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
	  document.forms[0].E01REMBNK.focus();
	  document.forms[0].E01REMBNK.select();
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
