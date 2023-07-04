<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Profile</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,java.math.*,com.datapro.eibs.parameters.loans.access.jdbc.bean.*,com.datapro.generic.beanutil.*" %>

<jsp:useBean id="insTblList" class="datapro.eibs.beans.JBObjList"  scope="session" />

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
        var ok= confirm("Desea borrar esta Tabla de Seguros ?");
        if (!ok) return;
     }
     document.forms[0].OPT.value = opt;
     document.forms[0].submit();
     
  }
  
  <% if (opt.equals("N") || opt.equals("M")) { %>
  	var page = "<%=request.getContextPath()%>/pages/s/Loans_insurance_maint.jsp?OPT=<%= opt%>";
  	CenterWindow(page,600,500,2);
  <% } %>
  
</SCRIPT>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.parameters.loans.servlet.JSInsurance">
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="3">
  <INPUT TYPE=HIDDEN NAME="ROW" value="0">
  <INPUT TYPE=HIDDEN NAME="OPT" value="">
  <INPUT TYPE=HIDDEN NAME="SELTYP" value="<%= request.getParameter("SELTYP") %>">
  <INPUT TYPE=HIDDEN NAME="SELTBL" value="<%= request.getParameter("SELTBL") %>">
  
  <% if (insTblList.getNoResult()) {%>
   <p align=center><b>No existen tablas de seguros </b></p>
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
      <TD class=TDBKG><a href="javascript:checkClose()">Salir</a></TD>
    </TR>
   </TABLE>
   
   <TABLE  id="mainTable" class="tableinfo">
	 <TR > 
	    <TD NOWRAP valign="top" >
      
		  <TABLE id="dataTable" width="100%">
		  	<TR id="trdark"> 
		      <TH ALIGN=CENTER NOWRAP></TH>
		      <TH ALIGN=CENTER NOWRAP>Tabla</TH>
		      <TH ALIGN=CENTER NOWRAP>Descripción</TH>		      
		    </TR>
		   
    <%
             insTblList.initRow();
             CNTRLINS msgList = null;
             String chk= "checked";
             while (insTblList.getNextRow()) {
                 msgList = (CNTRLINS) insTblList.getRecord();
    %>              
		  <TR>
			<TD NOWRAP><input type="radio" name="REFNUM" <%=chk%> value="<%= msgList.getINSTYP()%>" onClick="setRow(<%=insTblList.getCurrentRow()%>)"></TD>
			<TD NOWRAP><%=msgList.getINSTYP()%></TD>
			<TD NOWRAP><%=msgList.getINSDSC()%></TD>
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
