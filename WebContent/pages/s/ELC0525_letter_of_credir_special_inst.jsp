<html>
<head>
<title>Instrucciones Especiales</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "lcInst" class= "datapro.eibs.beans.ESD000005Message"  scope="session" />

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

<body>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
   out.println("<SCRIPT> initMenu(); </SCRIPT>");

%> 
<h3 align="center">Instrucciones Especiales<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="letter_of_credir_special_inst.jsp,ELC0525"></h3>
<hr size="4">
 <FORM method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSELC0525">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="104">
	<INPUT TYPE=HIDDEN NAME="reason">  
  <table class="tableinfo">
    <tr > 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="16%"> 
              <div align="right"><b>Numero de Carta de Credito :</b></div>
            </td>
            <td nowrap width="20%"> 
              <div align="left">
                <input type="text" name="E05ACC" size="14" maxlength="12" value="<%= lcInst.getE05ACC().trim()%>" readonly>
				<INPUT type="hidden" name="E01LCMACC" value="<%=lcInst.getE05ACC().trim()%>" readonly> 										                
              </div>
            </td>
            <td nowrap width="16%"> 
              <div align="right"><b>Producto : </b></div>
            </td>
            <td nowrap width="16%"> 
              <div align="left"><b>
                <input type="text" name="E02PRO" size="4" maxlength="4" readonly value="<%= userPO.getHeader1().trim()%>">
                </b> </div>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p>&nbsp; </p>
  <table class="tableinfo">
    <tr  > 
      <td> 
        <p align="center"> 
          <input type="text" name="E15DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE15DSC().trim()%>">
          <input type="text" name="E25DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE25DSC().trim()%>">
          <input type="text" name="E35DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE35DSC().trim()%>">
          <input type="text" name="E45DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE45DSC().trim()%>">
          <input type="text" name="E55DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE55DSC().trim()%>">
          <input type="text" name="E65DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE65DSC().trim()%>">
          <input type="text" name="E75DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE75DSC().trim()%>">
          <input type="text" name="E85DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE85DSC().trim()%>">
          <input type="text" name="E95DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE95DSC().trim()%>">
          <input type="text" name="E05DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getE05DSC().trim()%>">
          <input type="text" name="EA5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEA5DSC().trim()%>">
          <input type="text" name="EB5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEB5DSC().trim()%>">
          <input type="text" name="EC5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEC5DSC().trim()%>">
          <input type="text" name="ED5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getED5DSC().trim()%>">
          <input type="text" name="EE5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEE5DSC().trim()%>">
          <input type="text" name="EF5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEF5DSC().trim()%>">
          <input type="text" name="EG5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEG5DSC().trim()%>">
          <input type="text" name="EH5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEH5DSC().trim()%>">
          <input type="text" name="EI5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEI5DSC().trim()%>">
          <input type="text" name="EJ5DSC" size="80" maxlength="80" readonly="readonly" value="<%= lcInst.getEJ5DSC().trim()%>">
        </p>
      </td>
    </tr>
  </table>
    <p>
  </form>
</body>
</html>
