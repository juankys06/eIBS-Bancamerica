<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Branch</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<jsp:useBean id="bankList" class="datapro.eibs.beans.JBObjList"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<%
   String defbank = "";
   try {
     defbank = request.getParameter("DEFBANK");
     if (defbank.equals(null)) defbank="";
   }
   catch (Exception e) {
   	 defbank="";
   }
%>
<SCRIPT Language="Javascript">
  function getBranch(val){
   <% if (defbank.equals("")){%>
    document.forms[0].BRANCH.value="";
    document.forms[0].BNAME.value="";
    document.forms[0].DBALL.checked=false;
   <% } %>
 	parent.results.window.location.href="<%=request.getContextPath()%>/pages/s/MISC_search_wait.jsp?URL='<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSBranchProfile?SCREEN=2@BRNBNK=" + val + "'";   
  }
  
  function enter(branch) {	
	var form = top.opener.document.forms[0];	
	<% if (defbank.equals("")){%>
		if (trim(branch) == "" && !(document.forms[0].DBALL.checked)) {
	   		 alert("Seleccione una sucursal antes de realizar esta operación");
		} else {	    
			form[top.opener.fieldBranch].value = (document.forms[0].DBALL.checked)?"0":branch;
			form[top.opener.fieldBank].value = document.forms[0].BANK.options[document.forms[0].BANK.selectedIndex].value; 		
			form[top.opener.fieldFlag].value = (document.forms[0].DBALL.checked)?"*":"";		
			top.opener.goAction('A'); 
			top.close();
		}	
	<% } else {%>
		if (trim(branch) == "") {
	   		 alert("Seleccione una sucursal antes de realizar esta operación");
		} else {
			form[top.opener.fieldBranch].value = branch;
			form[top.opener.fieldBank].value = "<%=defbank%>";
			form[top.opener.fieldFlag].value = "";		
			top.opener.goAction('S');
			top.close(); 
		}
	<% } %>	

	
  }
</SCRIPT>

</head>
<body>
<h3>Sucursales</h3>
<form>
              
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr> 
            <td id="trdark" nowrap width="20%" align=right> 
              <b>Banco :</b>
            </td>
         	<td nowrap >
         	    <% if (defbank.equals("")){%>
         	    <select name="BANK" onchange="getBranch(this.options[this.selectedIndex].value)">         	    
         	    	<% bankList.initRow();
            		while (bankList.getNextRow()) {
             		String bank = (String) bankList.getRecord(); %>             
                    <option value="<%= bank%>"><%= bank%></option>
                    <% 	}%>
                 </select> 
                <%} else {%>
                 <input type="text" readonly name="BANK" size="4" maxlength="3" value="<%=defbank%>">	
                <% }%>
                
           </td>
     	  </tr>
     	  <tr> 
     	    <td id="trdark" nowrap width="20%" align=right valign=top> 
              <b>Sucursal :</b>
            </td>
         	<td nowrap> 
            	<input type="text" readonly name="BRANCH" size="4" maxlength="3" value="">
            	<input type="text" readonly name="BNAME" size="40" maxlength="30" value="">
         	</td>
     	  </tr>
  		</table>
  	  </td>
    </tr>
  </table>
  <% if (defbank.equals("")){%>
  <h4 style="text-align:center">
  	<input type="checkbox" name="DBALL" value="*">Todas las Sucursales</h4>
  <% }%>
  <p align="center"> 
    <input id="EIBSBTN" type=button name="Submit" value="Enviar" onclick="enter(document.forms[0].BRANCH.value)">
  </p>
  <SCRIPT Language="Javascript">
   <% if (defbank.equals("")){%>
    getBranch(document.forms[0].BANK.options[document.forms[0].BANK.selectedIndex].value);	
   <% } else {%>
    getBranch("<%=defbank%>");
   <% }%>
  </SCRIPT>
</form>
</body>
</html>
