<html>
<head>
<title>Narrative (79)</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="lcAdd" class="datapro.eibs.beans.ELC051003Message"  scope="session" />

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



	builtNewMenu(lc_maint_detail);


   builtHPopUp();

  function showPopUp(opth,field,bank,ccy,field1,field2,opcod) {
   init(opth,field,bank,ccy,field1,field2,opcod);
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
<h3 align="center">Narrativa (79)<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="letter_of_credit_79_info_m.jsp,ELC0525"></h3>
<hr size="4">


 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="27">
     <INPUT TYPE=HIDDEN NAME="E01LCMBNK" value=<%= lcAdd.getE03LCDBNK().trim()%>>
	<INPUT TYPE=HIDDEN NAME="reason">       
<table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Letter Type : </b></div>
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
              <div align="right"><b>Bank : </b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left"> 
                <input type="text" readonly name="E03LCDBNK" size="3" maxlength="2" value="<%= lcAdd.getE03LCDBNK().trim()%>">
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Letter Number : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b> 
                <input type="text" readonly name="E03LCDACC" size="21" maxlength="20" value="<%= lcAdd.getE03LCDACC().trim()%>">
				<INPUT type="hidden" name="E01LCMACC" value="<%=lcAdd.getE03LCDACC().trim()%>" readonly>                 
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
          <input type="text" name="E03LCDM01" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM01().trim()%>"><br>
          <input type="text" name="E03LCDM02" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM02().trim()%>"><br>
          <input type="text" name="E03LCDM03" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM03().trim()%>"><br>
          <input type="text" name="E03LCDM04" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM04().trim()%>"><br>
          <input type="text" name="E03LCDM05" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM05().trim()%>"><br>
          <input type="text" name="E03LCDM06" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM06().trim()%>"><br>
          <input type="text" name="E03LCDM07" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM07().trim()%>"><br>
          <input type="text" name="E03LCDM08" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM08().trim()%>"><br>
          <input type="text" name="E03LCDM09" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM09().trim()%>"><br>
          <input type="text" name="E03LCDM10" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM10().trim()%>"><br>
          <input type="text" name="E03LCDM11" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM11().trim()%>"><br>
          <input type="text" name="E03LCDM12" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM12().trim()%>"><br>
          <input type="text" name="E03LCDM13" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM13().trim()%>"><br>
          <input type="text" name="E03LCDM14" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM14().trim()%>"><br>
          <input type="text" name="E03LCDM15" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM15().trim()%>"><br>
          <input type="text" name="E03LCDM16" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM16().trim()%>"><br>
          <input type="text" name="E03LCDM17" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM17().trim()%>"><br>
          <input type="text" name="E03LCDM18" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM18().trim()%>"><br>
          <input type="text" name="E03LCDM19" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM19().trim()%>"><br>
          <input type="text" name="E03LCDM20" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM20().trim()%>"><br>
          <input type="text" name="E03LCDM21" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM21().trim()%>"><br>
          <input type="text" name="E03LCDM22" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM22().trim()%>"><br>
          <input type="text" name="E03LCDM23" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM23().trim()%>"><br>
          <input type="text" name="E03LCDM24" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM24().trim()%>"><br>
          <input type="text" name="E03LCDM25" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM25().trim()%>"><br>
          <input type="text" name="E03LCDM26" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM26().trim()%>"><br>
          <input type="text" name="E03LCDM27" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM27().trim()%>"><br>
          <input type="text" name="E03LCDM28" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM28().trim()%>"><br>
          <input type="text" name="E03LCDM29" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM29().trim()%>"><br>
          <input type="text" name="E03LCDM30" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM30().trim()%>"><br>
          <input type="text" name="E03LCDM31" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM31().trim()%>"><br>
          <input type="text" name="E03LCDM32" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM32().trim()%>"><br>
          <input type="text" name="E03LCDM33" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM33().trim()%>"><br>
          <input type="text" name="E03LCDM34" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM34().trim()%>"><br>
          <input type="text" name="E03LCDM35" size="51" maxlength="50" value="<%= lcAdd.getE03LCDM35().trim()%>"><br>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
