<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/graphical.jsp"> </SCRIPT>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session"/>

<script language="JavaScript">
function advanceSearch(){
	  document.forms[0].SCREEN.value = 300;
	  document.forms[0].submit();
	 }
function newTicket(){
	  document.forms[0].SCREEN.value = 4;
	  document.forms[0].TRANSCODE.value = document.forms[0].TRANSCODE1.value ;
	  document.forms[0].submit();
	 }

function validPortfolio(){
	var valRet = true;
	if (document.forms[0].SEARCHC.value == 'P' && trim(document.forms[0].CUSTOMER.value) != ''){
   		valRet= false;
		alert("invalid customer number");
	} 
	return valRet;
}

function validCustomer() {
	var valRet = true;
	if (trim(document.forms[0].PORTFOLIO.value) == '') {
   		document.forms[0].SEARCHC.value = 'C';
	} else {
	   document.forms[0].SEARCHC.value = 'P';
	}
}

function validOptions(opt) {
   document.forms[0].SEARCHC.value=opt;
}

function closeHiddenDivNew(){
	setVisibility(document.getElementById("hiddenDivNew"), "hidden");
}

function showHiddenDivNew(evt) {
	evt = (evt) ? evt : ((window.event) ? window.event : "");
 	
	var hiddenDivNew = document.getElementById("hiddenDivNew");

	var y=evt.clientY + document.body.scrollTop;
	var x=evt.clientX + document.body.scrollLeft;
     
	cancelBub(evt);

	moveElement(hiddenDivNew, y, x);
	setVisibility(hiddenDivNew, "visible");
	 
}

document.onclick= closeHiddenDivNew;


</script>
<META name="GENERATOR" content="IBM WebSphere Studio">
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
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.invest.JSEWD0309" onSubmit="return(validPortfolio())">
	<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
  <input type=HIDDEN name="SEARCHC" value="">
  <input type=HIDDEN name="opt" value="1">
  <input type=HIDDEN name="TRANSCODE" value="">

<div id="hiddenDivNew" class="hiddenDiv">
 <TABLE id=tbhelp style="border-top-width : 1px;border-right-width : 1px;border-bottom-width : 1px;border-left-width : 1px;
	border-color: #0b23b5;
	border-style : solid solid solid solid;
	filter:progid:DXImageTransform.Microsoft.dropshadow(OffX=3, OffY=3, Color='gray', Positive='true');">
	<tr id="trdark"> 
      <td align=CENTER width="18%"> 
        <div align="right">Transaction Type</div>
      </td>
      <td align=LEFT width="34%"> 
       <select name="TRANSCODE1">
        <option value="">Select One</option>
        <option value="1">Purchase</option>
        <option value="2">Sale</option>
        <option value="3">Transfer In</option>
        <option value="4">Transfer Out</option>
        <option value="5">Multiple Purchase</option>
		<option value="6">Multiple Sale</option>
       </select> 
      </td>
    </tr>
    
   
   <tr id="trclear">
   <TD ALIGN=center nowrap colspan="2">
	     <input id="EIBSBTN" type=button name="Submit3" value="Submit" onClick="newTicket()"> 
   </TD>
   
   </tr>
 </TABLE>
</div>

  <h3 align="center">Orden de Compra - Venta </h3>
  <table class="tbenter" height="55%" width="100%" border="0">
    <tr> 
      <td nowrap align="center"> 
        <table class="tableinfo" align="center" width="100%" >
          <tr> 
            <td> 
              <table width="100%">
                <tr tr id="trdark"> 
                  <td valign="middle" align="center" colspan="2" height=33> 
                    <div align="left"><b>Buscar por</b></div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap> 
                    <div align="right"> N&uacute;mero de Transacci&oacute;n : 
                    </div>
                  </td>
                  <td width="73%" nowrap> 
                    <input type="text" name="ORDERNUM"  size=9 maxlength="9" 
					  onChange="javascript:validOptions('O')">
                    o</td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap> 
                    <div align="right">Cliente : </div>
                  </td>
                  <td width="73%" nowrap> 
                    <input type="text" name="CUSTOMER"  size=9 maxlength="9" 
						onChange="javascript:validCustomer()">
                    <a href="javascript:GetCustomerDescId('CUSTOMER','','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                    y / o Portafolio 
                    <input type="text" name="PORTFOLIO"  size=6 maxlength="5" 
					onChange="javascript:validOptions('P')">
                    <a href="javascript:GetPortfolioNumDesc('PORTFOLIO','',document.forms[0].CUSTOMER.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0" ></a> 
                    o</td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap> 
                    <div align="right">Broker : </div>
                  </td>
                  <td nowrap width="73%"> 
                    <input type="text" name="BROKER"  size=4 maxlength="3" 
					onChange="javascript:validOptions('B')">
                    <input type="text" name="DESCR"  size=35 maxlength="35" 
					onChange="javascript:validOptions('B')">
                    <a href="javascript:GetBrokerI('BROKER','DESCR')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absmiddle" border="0"></a> 
                    o</td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap > 
                    <div align="right">Emisor : </div>
                  </td>
                  <td nowrap width="73%" > 
                    <input type="text" name="ISSUER"  size=35 maxlength="30" 
					onChange="javascript:validOptions('I')">
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap > 
                    <div align="right"> Tipo de Instrumento : </div>
                  </td>
                  <td nowrap width="73%" > 
                    <div align="left"> 
                      <select name="TYPE">
                        <option value=" ">Todos </option>
                        <option value="BND">Bonos</option>
                        <option value="EQT">Acciones</option>
                        <option value="MUT">Fondos Mutuos</option>
                        <option value="PFS">Acciones Preferenciales</option>
                      </select>
                    </div>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="27%" nowrap > 
                    <div align="right"> Tipo de Transacci&oacute;n : </div>
                  </td>
                  <td nowrap width="73%" > 
                    <select name="TRANSTYPE">
                      <option value="">Todos</option>
                      <option value="1">Compra</option>
                      <option value="2">Venta</option>
                      <option value="3">Transferencia de Entrada</option>
                      <option value="4">Transferencia de Salida</option>
                      <option value="5">Compras Múltiples</option>
					  <option value="6">Ventas Múltiples</option>
                    </select>
                  </td>
                </tr>
                <tr id="trclear"> 
                  <td width="31%" nowrap > 
                    <div align="right">Fecha de Orden : </div>
                  </td>
                  <td nowrap width="69%" > 
                    <div align="left"> 
                      <input type="text" size="2" maxlength="2" name="DATEF1" 
					  onChange="javascript:validOptions('D')">
                      <input type="text" size="2" maxlength="2" name="DATEF2" onKeyPress="enterInteger()">
                      <input type="text" size="2" maxlength="2" name="DATEF3" onKeyPress="enterInteger()">
                      <a href="javascript:DatePicker(document.forms[0].DATEF1,document.forms[0].DATEF2,document.forms[0].DATEF3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                      para 
                      <input type="text" size="2" maxlength="2" name="DATET1" 
					   onChange="javascript:validOptions('D')">
                      <input type="text" size="2" maxlength="2" name="DATET2" onKeyPress="enterInteger()">
                      <input type="text" size="2" maxlength="2" name="DATET3" onKeyPress="enterInteger()">
                      <a href="javascript:DatePicker(document.forms[0].DATET1,document.forms[0].DATET2,document.forms[0].DATET3)"><img src="<%=request.getContextPath()%>/images/calendar.gif" alt="help" align="absmiddle" border="0"></a> 
                    </div>
                  </td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" bordercolor="#FFFFFF">
          <tr bgcolor="#FFFFFF">
            <td width="33%">
              <div align="center">
                <input id="EIBSBTN" type=button name="Nueva" value="Nueva Orden">
              </div>
            </td>
            <td width="33%"> 
              <div align="center"> 
                <input id="EIBSBTN" type=submit name="Buscar" value="Buscar">
              </div>
            </td>
          </tr>
          <tr bgcolor="#FFFFFF">
            <td>&nbsp;</td>
            <td> 
              <div align="center"> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  
<SCRIPT Language="Javascript">
	
 	document.getElementById("hiddenDivNew").onclick=cancelBub;
 	getElementByNameAndId("Nueva", 'EIBSBTN').onclick=showHiddenDivNew;
</SCRIPT>
  
</form>
</body>
</html>
