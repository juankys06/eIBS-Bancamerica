<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="javascript">
//<!-- Hide from old browsers
function enter(acc) {
var form = top.opener.document.forms[0];
	form[top.opener.NewAcc].value = acc;  		
top.close();
 }
//-->
</script>

<TITLE>Old Accounts Help</TITLE>
</head>

<jsp:useBean id= "ewd0406Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if (ewd0406Help.getNoResult()) {
%>
 		<TABLE class="tbenter" width=100% height=100%>
 		<TR>
      <TD> 
        
      <div align="center"> <font size="3"><b> No hay datos que correspondan con su criterio de busqueda</b></font> 
      </div>
      </TD></TR>
   		</TABLE>
<%
	}
	else {
%>
 
  <TABLE class="tableinfo" style="width:95%" ALIGN=CENTER>
    <TR id="trdark"> 
      <TH ALIGN=CENTER  nowrap width="20%">Cuenta Vieja</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Cuenta Nueva</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Estado</TH>
      <TH ALIGN=CENTER  nowrap width="10">Tipo de<br>Producto</TH>
      <TH ALIGN=CENTER  nowrap width="40%">Cliente</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Nombre</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Banco</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Sucursal</TH>
      <TH ALIGN=CENTER  nowrap width="10">Moneda</TH>

      </TR>
    <%
                String selBank = (String)request.getAttribute("selBank");
                String selBranch = (String)request.getAttribute("selBranch");
                String selOldAcc = (String)request.getAttribute("selOldAcc");
                String selPrdType = (String)request.getAttribute("selPrdType");
                String selCurrency = (String)request.getAttribute("selCurrency");
                String selNewAcc = (String)request.getAttribute("selNewAcc");
                String selCustomer = (String)request.getAttribute("selCustomer");
               ewd0406Help.initRow();
                while (ewd0406Help.getNextRow()) {
                    if (ewd0406Help.getFlag().equals("")) {
                    		out.println(ewd0406Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0406Help.getShowPrev() ) {
      			int pos =ewd0406Help.getFirstRec() - 21;
      		 	 out.print("<A HREF=\"" + request.getContextPath() +
					"/servlet/datapro.eibs.helps.JSEWD0406?selOldAcc=" + selOldAcc + "&FromRecord=" + pos 
					+ "&selBank=" + selBank 
					+ "&selBranch=" + selBranch 
					+ "&selPrdType=" + selPrdType 
					+ "&selCurrency=" + selCurrency
					+ "&selNewAcc=" + selNewAcc 
					+ "&selCustomer=" + selCustomer
					+"\" > <img src=\"" + request.getContextPath() + "/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0406Help.getShowNext() ) {
      			int pos = ewd0406Help.getLastRec();
      		 	out.print("<A HREF=\"" + request.getContextPath() + 
					"/servlet/datapro.eibs.helps.JSEWD0406?selOldAcc=" + selOldAcc + "&FromRecord=" + pos 
					+ "&selBank=" + selBank 
					+ "&selBranch=" + selBranch 
					+ "&selPrdType=" + selPrdType 
					+ "&selCurrency=" + selCurrency
					+ "&selNewAcc=" + selNewAcc 
					+ "&selCustomer=" + selCustomer
					+ "\" > <img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

        }
%> </TD>
	 </TR>
	 </TABLE>
<%      
  }
%> 
</FORM>

</BODY>
</HTML>