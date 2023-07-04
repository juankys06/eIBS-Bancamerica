<html>
<head>
<title>Recepcion de Chequeras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<jsp:useBean id= "msgCHKB" class= "datapro.eibs.beans.ECH030601Message"  scope="session" />

<script language="JavaScript">

function validData(){
  var canCHK = trim(document.forms[0].E01CANCHK.value);
  var ok=false;   
  if (canCHK.length > 0){
	    if (parseInt(canCHK) > 0) ok = true;
  }
  if (!ok) {
    alert("La cantidad de chequeras debe ser valor mayor que 0");
	document.forms[0].E01CANCHK.focus();
  }
  return ok;
}

</script>

</head>
<body>

<H3 align="center">Solicitud de Chequeras sin Personalizacion<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_enter_req,ECH0306"></H3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0306" onsubmit="return(validData())">
   
 <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
 <INPUT TYPE=HIDDEN NAME="E01NUMSOL" VALUE="<%=msgCHKB.getE01NUMSOL()%>">
         
  
<table class="tableinfo">
  <tr> 
   <td nowrap>  
    <table class="tbenter" cellspacing=0 cellpadding=2 width="100%" border="0">    
    <tr id=trdark> 
      <td nowrap width="40%"> 
        <div align="right">Banco : </div>
      </td>
      <td nowrap > 
        <input type="text" name="E01CODBNK" size="3" maxlength="2" onKeypress="enterInteger()" value="<%=msgCHKB.getE01CODBNK()%>">
      </td>
    </tr>
    <tr id=trclear> 
      <td nowrap width="40%"> 
        <div align="right">Sucursal Destino : </div>
      </td>
      <td nowrap > 
        <input type="text" name="E01SUCDST" size="4" maxlength="3" onKeypress="enterInteger()" value="<%=msgCHKB.getE01SUCDST()%>">
        <a href="javascript:GetBranch('E01SUCDST',document.forms[0].E01CODBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
    </tr>
    <tr id=trdark> 
      <td nowrap > 
        <div align="right">Sucursal Origen : </div>
      </td>
      <td nowrap > 
        <input type="text" name="E01SUCORG" size="4" maxlength="3" onKeypress="enterInteger()" value="<%=msgCHKB.getE01SUCORG()%>">
        <a href="javascript:GetBranch('E01SUCORG',document.forms[0].E01CODBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
    </tr>
    <tr id=trclear> 
      <td nowrap> 
        <div align="right">Generar Numeraci&oacute;n : </div>
      </td>
      <td nowrap> 
        <input type="radio" name="E01NUMCTA" value="Y" <% if (!msgCHKB.getE01NUMCTA().equals("N")) out.print("checked");%>>Si
        <input type="radio" name="E01NUMCTA" value="N" <% if (msgCHKB.getE01NUMCTA().equals("N")) out.print("checked");%>>No
      </td>
    </tr>
    <tr id=trdark> 
      <td> 
         <div align="right">Moneda : </div>        
      </td>
      <td nowrap> 
      	 <input type="text" name="E01CODCCY" size="4" maxlength="3" value="<%=msgCHKB.getE01CODCCY()%>">
         <a href="javascript:GetCurrency('E01CODCCY',document.forms[0].E01CODBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
    </tr>
    <tr id=trclear> 
      <td> 
         <div align="right">Tipo Persona : </div>        
      </td>
      <td nowrap>
        <SELECT name="E01TIPPER">
            <OPTION value="1" <% if (!(msgCHKB.getE01TIPPER().equals("2") || msgCHKB.getE01TIPPER().equals("3"))) out.print("selected"); %>>Juridico</OPTION>
            <OPTION value="2" <% if (msgCHKB.getE01TIPPER().equals("2")) out.print("selected"); %>>Natural</OPTION>
        	<OPTION value="3" <% if (msgCHKB.getE01TIPPER().equals("3")) out.print("selected"); %>>No Aplica</OPTION>
        </SELECT> 
      </td>
    </tr>
    <tr id=trdark> 
      <td> 
         <div align="right">Tipo Chequera : </div>        
      </td>
      <td nowrap> 
      	 <input type="text" name="E01TIPCHK" size="4" maxlength="3" value="<%=msgCHKB.getE01TIPCHK()%>">
         <a href="javascript:GetTypCHKBook('E01TIPCHK','','',document.forms[0].E01CODCCY.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
    </tr>
    <tr id=trclear> 
      <td> 
         <div align="right">Cantidad Chequeras : </div>        
      </td>
      <td nowrap> 
      	 <input type="text" name="E01CANCHK" size="4" maxlength="3" onKeypress="enterInteger()" value="<%=msgCHKB.getE01CANCHK()%>">
      </td>
    </tr>
  </table>
 </td>
 </tr>
</table>
   <p align="center"> 
        <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
   </p>

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
