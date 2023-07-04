<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Group List</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<jsp:useBean id="groupList" class="datapro.eibs.beans.JBObjList"  scope="session" />


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">

  
  function showUsers(idx) {
    var val = document.forms[0].gpList.options[idx].value;
    if (val=="UNASSIGNED") val=" ";
    document.forms[0].GROUPID.value = val;
    document.forms[0].ROW.value = "" + idx;
    document.forms[0].submit();
  }
  
</SCRIPT>

</head>
<body style = "background-color :transparent; margin:0">
  <form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSGroupProfile" target="USRFRAME"> 
    
    <%    
    	  String lstGrp = "";
          String val = "";
          String sel = "selected";
          StringBuffer lst = new StringBuffer("");
          int row = 0;
          int sizeList = 0;
          try {
      		row = Integer.parseInt(request.getParameter("ROW"));
    	  }
          catch (Exception e) {
           row = 0;
          }
          groupList.initRow();
   		  while (groupList.getNextRow()) {
          	CNTRLBTH bthBean = (CNTRLBTH) groupList.getRecord();
          	if ( row == groupList.getCurrentRow()) sel = "selected"; else sel = "";
          	if (bthBean.getBTHKEY().equals("UNASSIGNED")) val ="SIN DEFINICION"; else val = bthBean.getBTHKEY();
          	lst.append("<option value=\""+ bthBean.getBTHKEY()+"\" "+ sel + ">"+ val +"</option>");    	                    
          }
          sizeList = groupList.getLastRec() + 1; 
          lstGrp = lst.toString();
          session.setAttribute("lstGrp",lstGrp);
     %> 
    <INPUT TYPE=HIDDEN NAME="ACTION" value="0">
	<INPUT TYPE=HIDDEN NAME="SCREEN" value="5">
	<INPUT TYPE=HIDDEN NAME="GROUPID" value="">
	<INPUT TYPE=HIDDEN NAME="ROW" value="<%= row %>">
							
    <table width="100%">
    
    <tr> 
        <td align=center valign=top>
  	       <select id="gpList" size =<%= sizeList %> style="width:150" onclick="showUsers(this.selectedIndex)"> 
 	          <%= lstGrp %>          
           </select>   
       </td>
       
 	  </tr>
 	  </table>
    
   
  </form>
  <SCRIPT Language="Javascript">
     showUsers(<%= row %>);
     <%
   int dberr = 0;
   try {
       dberr = Integer.parseInt(request.getParameter("ERR"));
   }
   catch (Exception e) {
       dberr = 0;
   }
   
   if (dberr == 1) {
  %>
   alert("Usuario ya existente.");
  <% }%>
  </SCRIPT>
</body>
</html>
