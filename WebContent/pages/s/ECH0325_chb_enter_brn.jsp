<html>
<head>
<title>Recepcion de Chequeras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

</head>
<body>

<H3 align="center"><% if (userPO.getOption().equals("E")) out.print("Envio de Chequeras a Sucursales"); else out.print("Chequeras Recibidas en Sucursales"); %><img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="chb_enter_maint,ECH0325"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSECH0325">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  </p>
  <table class="tableinfo">
   <tr> 
   <td>
    <table cellspacing=0 cellpadding=2 width="100%" border="0">    
     <tr id=trdark> 
      <td nowrap width="40%"> 
        <div align="right">Banco : </div>
      </td>
      <td nowrap colspan=2> 
        <input type="text" name="E01SELBNK" size="3" maxlength="2" onKeypress="enterInteger()">
      </td>
     </tr>
     <tr id=trclear> 
      <td nowrap width="40%"> 
        <div align="right">Oficina de Entrega : </div>
      </td>
      <td nowrap colspan=2> 
        <input type="text" name="E01SELBRN" size="4" maxlength="3" onKeypress="enterInteger()">
        <a href="javascript:GetBranch('E01SELBRN',document.forms[0].E01SELBNK.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
      </td>
     </tr>
     <tr id=trdark> 
      <td>         
      </td>
      <td><div align="rigth">Desde </div>
      </td>
      <td><div align="rigth">Hasta </div>
      </td>
     </tr>
     <tr id=trclear> 
      <td> 
         <div align="right">Fecha de Solicitud : </div>        
      </td>
      <td nowrap> 
      	  <input type="text" name="E01SELID1" size="3" maxlength="2" onKeypress="enterInteger()">
      	  <input type="text" name="E01SELID2" size="3" maxlength="2" onKeypress="enterInteger()">
      	  <input type="text" name="E01SELID2" size="3" maxlength="2" onKeypress="enterInteger()">
      </td>
      <td nowrap> 
      	  <input type="text" name="E01SELFD1" size="3" maxlength="2" onKeypress="enterInteger()">
      	  <input type="text" name="E01SELFD2" size="3" maxlength="2" onKeypress="enterInteger()">
      	  <input type="text" name="E01SELFD3" size="3" maxlength="2" onKeypress="enterInteger()">
      </td>
      </tr>
     </table>
    </td>
   </tr>
  </table>
  <br>
          <div align="center"> 
            <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
          </div>
<script language="JavaScript">
  document.forms[0].E01SELBNK.focus();
  document.forms[0].E01SELBNK.select();
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
