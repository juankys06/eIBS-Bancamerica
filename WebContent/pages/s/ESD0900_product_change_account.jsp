


<html> 
<head>
<title>Cambio de Producto</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>
<jsp:useBean id="brnDetails" class="datapro.eibs.beans.ESD090001Message"  scope="session" />

<script language="Javascript">
function cancel(){
	document.forms[0].SCREEN.value = 100;
	document.forms[0].submit();
}
</script>

</head>
<body>

 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
    }
%>
  <h3 align="center">Cambio de Producto<br>Cambio Individual en Cuenta<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="product_change_account.jsp, ESD0900"> 
  </h3>
  <hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSESD0900" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="600">
  <INPUT TYPE=HIDDEN NAME="CHANGE" VALUE="A">
  <INPUT TYPE=HIDDEN NAME="E01CHGTYP" VALUE="<% if (!brnDetails.getE01CHGTYP().equals("")) { out.print(brnDetails.getE01CHGTYP()); } else { out.print("1"); } %>">
  
  <table class="tableinfo">
      <tr> 
        <td nowrap> 
          <table cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
            <tr id="trdark"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Módulo :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGAPL" size="3" maxlength="2" value="<%= brnDetails.getE01CHGAPL() %>">
                <input type="text" name="E01MODDSC" size="36" maxlength="35" value="<%= brnDetails.getE01MODDSC() %>">
              	<a href="javascript:GetCodeDescCNOFC('E01CHGAPL','E01MODDSC','AP')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="ayuda" align="absbottom" border="0" ></a> 
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Tipo de Producto :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGPRT" size="5" maxlength="4" value="<%= brnDetails.getE01CHGPRT() %>" readonly>
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Número de Cuenta :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGACC" size="14" maxlength="13" value="<%= brnDetails.getE01CHGACC() %>">
        		<a href="javascript:GetAccountInfo('E01CHGACC','',document.forms[0].E01CHGAPL.value,'','','E01CUSNA1','E01CHGCCY','E01CHGPRT','E01CHGPRO')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="Ayuda" align="absbottom" border="0" ></a> 
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Moneda :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGCCY" size="4" maxlength="3" value="<%= brnDetails.getE01CHGCCY() %>" readonly>
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Producto Actual :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGPRO" size="5" maxlength="4" value="<%= brnDetails.getE01CHGPRO() %>" readonly>
                <input type="text" name="E01PRDDSC" size="36" maxlength="35" value="<%= brnDetails.getE01PRDDSC() %>" readonly>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Nuevo Producto :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGPRC" size="5" maxlength="4" value="<%= brnDetails.getE01CHGPRC() %>">
                <input type="text" name="E01NEWPRD" size="36" maxlength="35" value="<%= brnDetails.getE01NEWPRD() %>">
                <a href="javascript:GetProduct('E01CHGPRC',document.forms[0].E01CHGAPL.value,'','E01NEWPRD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Nombre del Cliente :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CUSNA1" size="36" maxlength="35" value="<%= brnDetails.getE01CUSNA1() %>" readonly>
              </td>
            </tr>
            <tr id="trclear"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Cuenta Contable Actual :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGOGL" size="18" maxlength="17" value="<%= brnDetails.getE01CHGOGL() %>">
                <input type="text" name="E01OLDGLD" size="36" maxlength="35" value="<%= brnDetails.getE01OLDGLD() %>">
              </td>
            </tr>
            <tr id="trdark"> 
              <td nowrap width="10%"> 
              </td>
              <td nowrap width="15%"> 
                <div align="right">Cuenta Contable Nueva :</div>
              </td>
              <td nowrap width="75%">  
                <input type="text" name="E01CHGNGL" size="18" maxlength="17" value="<%= brnDetails.getE01CHGNGL() %>">
                <input type="text" name="E01NEWGLD" size="36" maxlength="35" value="<%= brnDetails.getE01NEWGLD() %>">
                <a href="javascript:GetLedger('E01CHGNGL','','',document.forms[0].E01CHGAPL.value,'E01NEWGLD')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  <BR>
<p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
    <input id="EIBSBTN" type=button name="Cancel" value="Regresar" onClick="javascript:cancel()">
  </p>
      
</form>
</body>
</html>
