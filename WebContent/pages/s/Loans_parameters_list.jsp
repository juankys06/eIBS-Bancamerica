<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,java.math.*,com.datapro.eibs.parameters.loans.access.jdbc.bean.*,com.datapro.generic.beanutil.*" %>

<jsp:useBean id="listLNParam" class="datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 
 String opt = "";
 
 try {
    if (request.getParameter("OPT") != null) opt = request.getParameter("OPT");
 } catch (Exception e) {
    opt="";
 }      
%> 

<SCRIPT Language="Javascript">

  function setRow(idxRow){
   var i = parseInt(document.forms[0].ROW.value);
   dataTable.rows[i + 1].className="trnormal";
   dataTable.rows[idxRow + 1].className="trhighlight";
   document.forms[0].ROW.value = "" + idxRow;
  }
  
  function goAction(opt) {
     
     if (opt =="D") {
        var ok= confirm("Desea borrar esta Tabla de Parametros ?");
        if (!ok) return;
     }
     document.forms[0].OPT.value = opt;
     document.forms[0].submit();
     
  }
  
  <% if (opt.equals("N") || opt.equals("M")) { %>
  	var page = "<%=request.getContextPath()%>/pages/s/Loans_parameters_maint.jsp?OPT=<%= opt%>&SELTYP=<%= request.getParameter("SELTYP") %>";
  	CenterWindow(page,600,500,2);
  <% } %>
  
</SCRIPT>
</head>
<body>

<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.parameters.loans.servlet.JSLoansParameters">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="3">
  <INPUT TYPE=HIDDEN NAME="ROW" value="0">
  <INPUT TYPE=HIDDEN NAME="OPT" value="">
  <INPUT TYPE=HIDDEN NAME="SELTYP" value="<%= request.getParameter("SELTYP") %>">
  <INPUT TYPE=HIDDEN NAME="SELBNK" value="<%= request.getParameter("SELBNK") %>">
  <INPUT TYPE=HIDDEN NAME="SELPRD" value="<%= request.getParameter("SELPRD") %>">
  <INPUT TYPE=HIDDEN NAME="SELTBL" value="<%= request.getParameter("SELTBL") %>">
  <% if (request.getParameter("SELTYP").equals("2")) {%>
  <INPUT TYPE=HIDDEN NAME="SELCUN" value="<%= request.getParameter("SELCUN") %>">  
  <% }
  	if (listLNParam.getNoResult()) {%>
   <TABLE class="tbenter">
    <TR> 
      <TD class=TDBKG><a href="javascript:goAction('N')">Nueva</a></TD>
      <TD class=TDBKG><a href="javascript:parent.checkClose()">Salir</a></TD>
    </TR>
   </TABLE>
  <% } else {%>
   <TABLE class="tbenter">
    <TR>
      <TD class=TDBKG><a href="javascript:goAction('M')">Modificar</a></TD>
      <TD class=TDBKG><a href="javascript:goAction('D')">Borrar</a></TD>
      <TD class=TDBKG><a href="javascript:parent.checkClose()">Salir</a></TD>
    </TR>
   </TABLE>
   
   <TABLE  id="mainTable" class="tableinfo">
	 <TR > 
	    <TD NOWRAP valign="top" >
      
		  <TABLE id="dataTable" width="100%">
		  	<TR id="trdark"> 
		      <TH ALIGN=CENTER NOWRAP></TH>
		      <TH ALIGN=CENTER NOWRAP>Banco</TH>
			  <TH ALIGN=CENTER NOWRAP>Producto</TH>
			  <TH ALIGN=CENTER NOWRAP>Tabla</TH>
		      <TH ALIGN=CENTER NOWRAP>Descripción</TH>
		      <TH ALIGN=CENTER NOWRAP>Cliente</TH>
		    </TR>
		   
    <%
             listLNParam.initRow();
             DLSPARAMBean msgList = null;
             String chk= "checked";
             while (listLNParam.getNextRow()) {
                 msgList = (DLSPARAMBean) listLNParam.getRecord();
    %>              
		  <TR>
			<TD NOWRAP><input type="radio" name="REFNUM" <%=chk%> value="<%= msgList.getDLSKEY()%>" onClick="setRow(<%=listLNParam.getCurrentRow()%>)"></TD>
			<TD NOWRAP><%=msgList.getDLSKEY().substring(0,2)%></TD>
			<TD NOWRAP><%=msgList.getDLSKEY().substring(4,8)%></TD>
			<TD NOWRAP><%=msgList.getDLSKEY().substring(2,4)%></TD>
			<TD NOWRAP><%=msgList.getDLSDSC()%></TD>
			<TD NOWRAP><%=msgList.getDLSCUN()%></TD>
		   </TR>         
    <%           
     				chk="";  
                }
    %> 
   		</TABLE>

   </TD>
  </TR>
  </TABLE>
  <SCRIPT language="JavaScript">  
  	showChecked("REFNUM");
  </SCRIPT>
  <% }%>
  </form>
</body>
</html>
