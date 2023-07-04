<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>
Transferencias
</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "appList" class= "datapro.eibs.beans.JBList"   scope="session"/>

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT language="JavaScript">
	setTimeout("top.close()", <%= datapro.eibs.master.JSEIBSProp.getPopUpTimeOut() %>)
</SCRIPT>

<script language="javascript">
//<!-- Hide from old browsers
function enter(code) {
var formLength= top.opener.document.forms[0].elements.length;
for(n=0;n<formLength;n++){
var elementName= top.opener.document.forms[0].elements[n].name;
	if(elementName == top.opener.fieldName){
  		top.opener.document.forms[0].elements[n].value = code;
  		break;
	}
 }
top.close();
 }
//-->
</script>

</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<BODY onload="">
<h3 align="center">Transacciones Financieras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="pr_inq_list.jsp,EPR1060"> 
</h3>
<hr size="4">
<FORM Method="post">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
<INPUT TYPE=HIDDEN NAME="REFNUM" VALUE="">
<INPUT TYPE=HIDDEN NAME="totalRow" VALUE="0">

  <TABLE class="tableinfo" align="center" style="width:'95%'">
    <TR id="trdark"> 
      <TH ALIGN=CENTER NOWRAP>N&uacute;mero de Referencia</TH>
      <TH ALIGN=CENTER NOWRAP>Monto</TH>
      <TH ALIGN=CENTER NOWRAP>Status</TH>
      <TH ALIGN=CENTER NOWRAP>Beneficiario </TH>
      <TH ALIGN=CENTER NOWRAP>Banco Beneficiario</TH>
      <TH ALIGN=CENTER NOWRAP>Fecha</TH>
    </tr>
    <%
                appList.initRow();
                int k=1;
                while (appList.getNextRow()) {
                    if (appList.getFlag().equals("")) {
                    		out.println(appList.getRecord());
                    k++;
                    }        
                }
    %> 
  </TABLE>
 


</FORM>

</BODY>
</HTML>
