<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Commercial Paper Market Value Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "prList" class= "datapro.eibs.beans.JBObjList"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<SCRIPT Language="javascript">


function goProcess() {

	document.forms[0].submit();
			 
}

</SCRIPT>
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

<h3 align="center">Commercial Paper Market Value Maintenance<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="market_value_list.jsp, EDL0115"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.products.JSEDL0115">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="NEXTROWS" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="CURRROWS" VALUE="0">

<% 
	if ( prList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> There are no items.</h4> 
      </div>
      </TD></TR>
   	</TABLE>
<%
	}
	else {
%>    
  <TABLE class="tbenter">
    <TR>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<a href="javascript:goProcess()">Process</a>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Exit</a>
      </TD>
    </TR>
  </TABLE>
  
  <TABLE class="tableinfo" id="dataTable">
    
        <TR id=trdark>
          <td NOWRAP align="center" ><b>Account</b></td>		  
		  <td NOWRAP align="center" ><b>Customer<br>Number</b></td>
          <td NOWRAP align="center" ><b>Customer<br>Name</b></td>		  		  		  
          <td NOWRAP align="center" ><b>Market<br>Value</b></td>
          <td NOWRAP align="center" ><b>New<br>Value</b></td>
          <td NOWRAP align="center" ><b>Common<br>Code</b></td>
          <td NOWRAP align="center" ><b>Invest<br>Type</b></td>
		</TR>
 
        <%
    	  int i = 0;
          prList.initRow();    
          while (prList.getNextRow()) {
               EDL011501Message msgList = (EDL011501Message) prList.getRecord();			 
         %>             
          <TR id=trclear>
          <td NOWRAP align="right" >
          	<%=Util.formatCell(msgList.getE01DEAACC())%>&nbsp;
          	<INPUT TYPE=HIDDEN NAME="E01DEAACC_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01DEAACC() %>">
          	<INPUT TYPE=HIDDEN NAME="E01DEACUN_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01DEACUN() %>">
          	<INPUT TYPE=HIDDEN NAME="E01CUSNA1_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01CUSNA1() %>">
          	<INPUT TYPE=HIDDEN NAME="E01DEAMVL_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01DEAMVL() %>">
          	<INPUT TYPE=HIDDEN NAME="E01DEAREF_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01DEAREF() %>">
          	<INPUT TYPE=HIDDEN NAME="E01DEAUC6_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01DEAUC6() %>">
 		  </td>		
          <td NOWRAP align="left" >
          	<%=Util.formatCell(msgList.getE01DEACUN())%>
		  </td>		  		  		  
          <td NOWRAP align="left" >
          	<%=Util.formatCell(msgList.getE01CUSNA1())%>
		  </td>		  		  		  
          <td NOWRAP align="right" >
          	<%=Util.formatCCY(msgList.getE01DEAMVL())%>
		  </td>		  		  		   		    
		  <td NOWRAP align="right" >
		  	<input type="text" name="E01DEANWV_<%= prList.getCurrentRow() %>" value="<%= msgList.getE01DEANWV() %>" size="11" maxlength="10" >
		  </td>
          <td NOWRAP align="left" >
          	<%=Util.formatCell(msgList.getE01DEAREF())%>
		  </td>	
          <td NOWRAP align="left" >
          	<%=Util.formatCell(msgList.getE01DEAUC6())%>
		  </td>		  		  		  		  	  		  		  
		</TR>
       <% 
       	 i++; 
        } 
       %> 
  </TABLE>
  <TABLE  class="tbenter" WIDTH="88%" ALIGN=CENTER>
   	 <TR>
      <TD WIDTH="50%" ALIGN=LEFT height="25"> <%
    if ( prList.getShowPrev() ) {
  		int pos = prList.getFirstRec() - 51;
  		out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSEDL0115?SCREEN=1&FromRecord=" + pos + "\" > <img src=\""+request.getContextPath()+"/images/e/previous_records.gif\" border=0></A>");
    } %>
      </TD>
 	  <TD WIDTH="50%" ALIGN=RIGHT height="25"> <%       
    if (prList.getShowNext()) {
  		int pos = prList.getLastRec();
  		out.print("<A HREF=\""+request.getContextPath()+"/servlet/datapro.eibs.products.JSEDL0115?SCREEN=1&FromRecord=" + pos +  "\" ><img src=\""+request.getContextPath()+"/images/e/next_records.gif\" border=0></A>");

    } %>
  </TD>
 </TR>
 </TABLE>
  
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
	document.forms[0].NEXTROWS.value = <%= prList.getLastRec()%>;
	document.forms[0].CURRROWS.value = <%= prList.getFirstRec()%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
