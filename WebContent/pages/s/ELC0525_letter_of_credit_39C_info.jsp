<html>
<head>
<title>Addtional Amounts Covered (39C)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lcAdd" class="datapro.eibs.beans.ELC050003Message"  scope="session" />

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<SCRIPT LANGUAGE="javascript">
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


<%if (!userPO.getPurpose().equals("NEW")){%>

	builtNewMenu(lc_approbal_detail);

<%}%>

   builtHPopUp();

  function showPopUp(opth,field,Banco,ccy,field1,field2,opcod) {
   init(opth,field,Banco,ccy,field1,field2,opcod);
   showPopupHelp();
   }
   
</SCRIPT>

</head>

<body bgcolor="#FFFFFF">


<% 
 if ( !error.getERRNUM().equals("0")  ) {
     out.println("<SCRIPT Language=\"Javascript\">");
     error.setERRNUM("0");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getPurpose().equals("NEW") ) {
    out.println("<SCRIPT> initMenu();  </SCRIPT>");
 }
%> 
<h3 align="center">Montos Adiconales Cubiertos (39C)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="letter_of_credit_39C_info.jsp,ELC0525"></h3>
<hr size="4">


<FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="29">
  <INPUT TYPE=HIDDEN NAME="E01LCMBNK" value=<%= lcAdd.getE03LCDBNK().trim()%>>
	<INPUT TYPE=HIDDEN NAME="reason">  
  <INPUT type="hidden" name="E01LCMACC" value="<%=lcAdd.getE03LCDACC().trim()%>" readonly> 										                
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E03LCDPRO" size="5" maxlength="4" value="<%= lcAdd.getE03LCDPRO().trim()%>" readonly>
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"></div>
            </td>
            <td nowrap width="16%"> 
            </td>
          </tr>        
          <tr id="trclear"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Banco : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%= lcAdd.getE03LCDBNK().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Numero Carta de Credito : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E03LCDACC" size="21" maxlength="20" value="<%= lcAdd.getE03LCDACC().trim()%>">
                </b> </div>
            </td>
          </tr>               
        </table>
      </td>
    </tr>
  </table>
<BR>
<table class="tableinfo">
    <tr > 
      <td nowrap align="center"> 
          <input type="text" name="E03LCDM01" size="45" maxlength="35" readonly value="<%= lcAdd.getE03LCDM01().trim()%>"><br>
          <input type="text" name="E03LCDM02" size="45" maxlength="35" readonly value="<%= lcAdd.getE03LCDM02().trim()%>"><br>
          <input type="text" name="E03LCDM03" size="45" maxlength="35" readonly value="<%= lcAdd.getE03LCDM03().trim()%>"><br>
          <input type="text" name="E03LCDM04" size="45" maxlength="35" readonly value="<%= lcAdd.getE03LCDM04().trim()%>"><br>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
