<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<%@ page import = "datapro.eibs.master.*,datapro.eibs.beans.*" %>
<title>Procesar Reportes</title>
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

<h3 align="center">Procesar Reportes<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="report_demand.jsp, EDD0924"></h3>
<hr size="4">

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.approval.JSERPTSTD">

  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="27">
  <INPUT TYPE=HIDDEN NAME="actRow" VALUE="0">
  <INPUT TYPE=HIDDEN NAME="TOTROWS" VALUE="0">

<% 
	if ( prList.getNoResult() ) {
%>
 	<TABLE class="tbenter" width=100% height=30%">
 		<TR>
      <TD>         
      <div align="center"> <h4 style="text-align:center"> No existen parámetros a enviar.</h4> 
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
  		<a href="javascript:goProcess()">Procesar</a>
      </TD>
      <TD ALIGN=CENTER class="TDBKG" width="50%">
  		<a href="<%=request.getContextPath()%>/pages/background.jsp">Salir</a>
      </TD>
    </TR>
  </TABLE>
  
  <TABLE class="tableinfo" id="dataTable">
    
    <%
    	  int i = 0;
    	  boolean firstTime = true; 	
          prList.initRow();    
          while (prList.getNextRow()) {
               ERP001001Message msgList = (ERP001001Message) prList.getRecord();			 
               
               if (firstTime) {     
       %>        
          <TR id=trdark>
            <td NOWRAP align=center colspan="3">
          	  <b><%=msgList.getE01RPTDSC()%></b>
		    </td>		  
		  </TR>
          <TR id=trdark>
          <td NOWRAP align="center" ><b>Parámetro</b></td>		  
		  <td NOWRAP align="center" ><b>Valor</b></td>
          <td NOWRAP align="center" ><b>Comentario</b></td>		  		  		  
		</TR>
            
       <%
       			firstTime = false;
       			}
       %>             
          <TR id=trclear>
          <td NOWRAP align="right" >
          	<%=Util.formatCell(msgList.getE01PMSDSC())%>&nbsp;:
          	<INPUT TYPE=HIDDEN NAME="E01RPTNME_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01RPTNME() %>">
          	<INPUT TYPE=HIDDEN NAME="E01RPTPRG_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01RPTPRG() %>">
          	<INPUT TYPE=HIDDEN NAME="E01RPTSEQ_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01RPTSEQ() %>">
          	<INPUT TYPE=HIDDEN NAME="E01RPTCOD_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01RPTCOD() %>">
          	<INPUT TYPE=HIDDEN NAME="E01RPTINS_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01RPTINS() %>">
          	<INPUT TYPE=HIDDEN NAME="E01PMSPLN_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01PMSPLN() %>">
          	<INPUT TYPE=HIDDEN NAME="E01PMSPTY_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01PMSPTY() %>">
          	<INPUT TYPE=HIDDEN NAME="E01PMSNDC_<%= prList.getCurrentRow() %>" VALUE="<%= msgList.getE01PMSNDC() %>">
 		  </td>		  
		  <td NOWRAP align="left" >
		  	<input type="text" name="E01PMSVAL_<%= prList.getCurrentRow() %>" value="<%= msgList.getE01PMSVAL() %>" 
		  		size="<%= msgList.getE01PMSPLN() %>" maxlength="<%= msgList.getE01PMSPLN() %>"    
		  		<% if ( msgList.getE01PMSPTY().equals("N")) out.print("onKeypress=\"enterInteger()\""); 
		  		   else if ( msgList.getE01PMSPTY().equals("Z")) out.print("onKeypress=\"enterDecimal()\"");%>>
		  </td>
          <td NOWRAP align="left" >
          	<%=Util.formatCell(msgList.getE01RPTINS())%>
		  </td>		  		  		  
		</TR>
       <% 
       	 i++; 
        } 
       %> 
  </TABLE>
<BR>
<SCRIPT Language="javascript">
	document.forms[0].TOTROWS.value = <%= i%>;
</SCRIPT>
<%      
  }
%> 
</form>
</body>
</html>
