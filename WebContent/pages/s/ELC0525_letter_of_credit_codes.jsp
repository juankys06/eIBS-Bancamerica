<html>
<head>
<title>Codigos Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "lcCodes" class= "datapro.eibs.beans.ESD000002Message" scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

function goMsgSwift() {
    
     
     if (document.forms[0].E01LCMACC.value !== "") {
         
		   var dx = 450;
		   var dy = 350;
		   var y0 = (screen.height - dy) / 2;
		   var x0 = (screen.width - dx) / 2;
		   var attr = 'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=no,left=' + x0 + ',top=' + y0 + ',height=' + dy + ',width=' + dx;

		   page = webapp + "/servlet/datapro.eibs.products.JSELC0525?SCREEN=0&E01LCMACC="+document.forms[0].E01LCMACC.value;
		   listin = window.open(page,'',attr);
         
     } else {
		  alert("Seleccione una cuenta ");	   
     }

 }


function goDetail(scr){
	document.forms[0].SCREEN.value = scr;
	document.forms[0].submit();
}

 function setReason(op, _reason){
 	option = op;
 	reason  = _reason;
	var page= prefix +language + 'ELC0525_enter_reason_text.jsp';
	CenterWindow(page,500,430,3);
 }

 function goAction(op) {
	if(op == "A"){
		document.forms[0].SCREEN.value = 1;
		document.forms[0].submit();		 
	}else if(op == "D"){
		document.forms[0].SCREEN.value = 2;
		document.forms[0].submit();
	}else if(op == "R"){
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}else if(op == "V"){
		document.forms[0].SCREEN.value = 4;
		document.forms[0].submit();
	}
 }



	builtNewMenu(lc_approbal_detail);


</SCRIPT>
</head>
<body bgcolor="#FFFFFF">

 
 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
    out.println("<SCRIPT> initMenu(); </SCRIPT>");

%>

<h3 align="center">C&oacute;digos de Clasificaci&oacute;n</h3>
<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="letter_of_credit_codes.jsp,ELC0525" width="35" height="35"> 
<hr size="4">
 <FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
	<INPUT TYPE=HIDDEN NAME="reason"> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="102">
  <INPUT TYPE=HIDDEN NAME="E01LCMBNK" value=<%= request.getParameter("E01LCMBNK").trim()%>>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap> 
        <TABLE cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <TBODY><TR id="trdark"> 
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Numero de Carta de Credito:</B></DIV>
            </TD>
            <TD nowrap width="20%"> 
              <DIV align="left"> 
                <INPUT type="text" name="E02ACC" size="12" maxlength="12" value="<%= lcCodes.getE02ACC().trim()%>" readonly>
				<INPUT type="hidden" name="E01LCMACC" value="<%=lcCodes.getE02ACC().trim()%>" readonly> 										
              </DIV>
            </TD>
            <TD nowrap width="16%"> 
              <DIV align="right"><B>Producto: </B></DIV>
            </TD><TD nowrap width="16%"> 
              <DIV align="left"><B> 
                <INPUT type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </B> </DIV>
            </TD>
            
          </TR>
        </TBODY></TABLE>
      </td>
    </tr>
  </table>
  <h4>C&oacute;digos de Clasificaci&oacute;n</h4>
  <table  class="tableinfo">
    <tr bordercolor="#FFFFFF"> 
      <td nowrap height="43"> 
        <table cellspacing=0 cellpadding=2 width="100%" border="0" height="118">
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Oficial Principal:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02OFC" size="5" maxlength="4" value="<%= lcCodes.getE02OFC().trim()%>" readonly>
              <input type="text" name="D02OFC" size="40" maxlength="35" value="<%= lcCodes.getD02OFC().trim()%>" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0" > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%" height="37"> 
              <div align="right">Oficial Secundario:</div>
            </td>
            <td nowrap width="70%" height="37"> 
              <input type="text" name="E02OF2" size="5" maxlength="4" value="<%= lcCodes.getE02OF2().trim()%>" readonly>
              <input type="text" name="D02OF2" size="40" maxlength="35" value="<%= lcCodes.getD02OF2().trim()%>" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0"  > 
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Industria:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02INC" size="5" maxlength="4" value="<%= lcCodes.getE02INC().trim()%>" readonly>
              <input type="text" name="D02INC" size="40" maxlength="35" value="<%= lcCodes.getD02INC().trim()%>" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="3%"> 
              <div align="right">Linea de Negocio:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02BUC" size="5" maxlength="4" value="<%= lcCodes.getE02BUC().trim()%>" readonly>
              <input type="text" name="D02BUC" size="40" maxlength="35" value="<%= lcCodes.getD02BUC().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Residencia:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GEC" size="5" maxlength="4" value="<%= lcCodes.getE02GEC().trim()%>" readonly>
              <input type="text" name="D02GEC" size="40" maxlength="35" value="<%= lcCodes.getD02GEC().trim()%>" readonly>
               
              <img src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio"  border="0"  > 
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Pa&iacute;s de Riesgo:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02GRC" size="5" maxlength="4" value="<%= lcCodes.getE02GRC().trim()%>" readonly>
              <input type="text" name="D02GRC" size="40" maxlength="35" value="<%= lcCodes.getD02GRC().trim()%>" readonly>
               
            </td>
          </tr>

          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">Origen de los Fondos</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02ORG" size="5" maxlength="4" value="<%= lcCodes.getE02ORG().trim()%>" readonly> 
              <input type="text" name="D02ORG" size="40" maxlength="35" value="<%= lcCodes.getD02ORG().trim()%>" readonly>
               
              <%if(userPO.getType().trim().equals("I")){%>
              	<IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="campo obligatorio" border="0">
              	<%}%></td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">Destino de los fondo:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02DST" size="5" maxlength="4" value="<%= lcCodes.getE02DST().trim()%>" readonly>
              <input type="text" name="D02DST" size="40" maxlength="35" value="<%= lcCodes.getD02DST().trim()%>" readonly>
               
            <%if(!userPO.getType().trim().equals("I")){%>
              	<IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="campo obligatorio" border="0">
              	<%}%></td>
          </tr>


          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 1:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC1" size="5" maxlength="4" value="<%= lcCodes.getE02UC1().trim()%>" readonly>
              <input type="text" name="D02UC1" size="40" maxlength="35" value="<%= lcCodes.getD02UC1().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 2:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC2" size="5" maxlength="4" value="<%= lcCodes.getE02UC2().trim()%>" readonly>
              <input type="text" name="D02UC2" size="40" maxlength="35" value="<%= lcCodes.getD02UC2().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 3:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC3" size="5" maxlength="4" value="<%= lcCodes.getE02UC3().trim()%>" readonly>
              <input type="text" name="D02UC3" size="40" maxlength="35" value="<%= lcCodes.getD02UC3().trim()%>" readonly>
               
            </td>
          </tr>
          <tr id="trclear"> 
            <td nowrap width="30%"> 
              <div align="right">C&oacute;digo de Usuario 4:</div>
            </td>
            <td nowrap width="70%"> 
              <input type="text" name="E02UC4" size="5" maxlength="6" value="<%= lcCodes.getE02UC4().trim()%>" readonly>
              <input type="text" name="D02UC4" size="40" maxlength="35" value="<%= lcCodes.getD02UC4().trim()%>" readonly>
               
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
</form>
</body>
</html>

