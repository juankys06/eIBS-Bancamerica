<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="No-cache">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
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
<TITLE></TITLE>
</head>
<jsp:useBean id= "ewd0011Help" class= "datapro.eibs.beans.JBList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />
<body>
 
 <script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>


<FORM>
<%
	if ( ewd0011Help.getNoResult() ) {
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
      <TH ALIGN=CENTER  nowrap width="10%">Centro de Costo</TH>
      <TH ALIGN=CENTER  nowrap width="20%">Cuenta de Presupuesto</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Moneda</TH>
      <TH ALIGN=CENTER  nowrap width="10%">Banco</TH>
      <TH ALIGN=CENTER  nowrap width="50%">Descripcion Cuenta</TH>
    </TR>
    <%
                String FromRecord = (String)request.getAttribute("FromRecord");
                String BankNumber = (String)request.getAttribute("BankNumber");
                String Currency = (String)request.getAttribute("Currency");
                String BudgetNum = (String)request.getAttribute("BudgetNum");
                ewd0011Help.initRow();
                while (ewd0011Help.getNextRow()) {
                    if (ewd0011Help.getFlag().equals("")) {
                    		out.println(ewd0011Help.getRecord());
                    }
                }
              %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="98%" ALIGN=CENTER>
    <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
        if ( ewd0011Help.getShowPrev() ) {
      			int pos =ewd0011Help.getFirstRec() - 21;
      			 out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0011?BankNumber=" + BankNumber + "&Currency=" + Currency + "&BudgetNum=" + BudgetNum + "&FromRecord=" + pos + "\" ><img src=\""+request.getContextPath()+"/images/s/previous_records.gif\" border=0></A>");
        }
%> </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
        if ( ewd0011Help.getShowNext() ) {
      			int pos = ewd0011Help.getLastRec();
      			out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.helps.JSEWD0011?BankNumber=" + BankNumber + "&Currency=" + Currency + "&BudgetNum=" + BudgetNum + "&FromRecord=" + pos + "\" ><img src=\""+request.getContextPath()+"/images/s/next_records.gif\" border=0></A>");

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