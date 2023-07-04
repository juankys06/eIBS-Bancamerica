<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="javascript">
//<!-- Hide from old browsers
function enter(bnk,brn,ccy,gln,acc) {
var form = top.opener.document.forms[0];
	form[top.opener.bank].value = bnk;
	form[top.opener.branch].value = brn;
	form[top.opener.currency].value = ccy;
	form[top.opener.glNumber].value = gln;
	form[top.opener.account].value = acc;  		
top.close();
 }
//-->
</script>

<TITLE>Amortization Accounts Help</TITLE>
</head>

<jsp:useBean id= "ewd0405Help" class= "datapro.eibs.beans.JBList"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<FORM>
<%
	if (ewd0405Help.getNoResult()) {
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
      <TH ALIGN=CENTER  nowrap width="10%">Banco</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Sucursal</TH>
      <TH ALIGN=CENTER  nowrap width="10">Moneda</TH>
      <TH ALIGN=CENTER  nowrap width="20%">Cuenta Contable</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Cuenta</TH>
      <TH ALIGN=CENTER  nowrap width="40%">Descripción</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Clase</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Tipo</TH>
      </TR>
    <%
                String shrBank = (String)request.getAttribute("shrBank");
                String shrBranch = (String)request.getAttribute("shrBranch");
                String NameSearch = (String)request.getAttribute("NameSearch");
                String shrAppCode = (String)request.getAttribute("shrAppCode");
                String shrCurrency = (String)request.getAttribute("shrCurrency");
                ewd0405Help.initRow();
                while (ewd0405Help.getNextRow()) {
                    if (ewd0405Help.getFlag().equals("")) {
                    		out.println(ewd0405Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0405Help.getShowPrev() ) {
      			int pos =ewd0405Help.getFirstRec() - 21;
      		 	 out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0405?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&shrBank=" + shrBank + "&shrBranch=" + shrBranch + "&shrAppCode=" + shrAppCode + "&shrCurrency=" + shrCurrency +"\" > <img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0405Help.getShowNext() ) {
      			int pos = ewd0405Help.getLastRec();
      		 	out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0405?NameSearch=" + NameSearch + "&FromRecord=" + pos + "&shrBank=" + shrBank + "&shrBranch=" + shrBranch + "&shrAppCode=" + shrAppCode + "&shrCurrency=" + shrCurrency + "\" > <img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

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