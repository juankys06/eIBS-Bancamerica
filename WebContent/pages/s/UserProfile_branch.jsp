<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Branch</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<jsp:useBean id="branchList" class="datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>


<%
   branchList.initRow();
   branchList.setCurrentRow(0);
   CNTRLDBANME branchBean = (CNTRLDBANME) branchList.getRecord();    	
 %>
<SCRIPT Language="Javascript">

  var user_opt =
 "<A HREF= <%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile?SCREEN=1&userid=<%= branchBean.getDBAUSR()%>>Informaci&oacute;n B&aacute;sica</A><BR>"+
 "<A HREF= <%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile?SCREEN=3&userid=<%= branchBean.getDBAUSR()%>>Privilegios</A><BR>"+
 "<A HREF= javascript:checkClose()>Salir</A>"; 
   
  builtNewMenu(user_opt);
  initMenu();
  
  function getNewBranch(){
    page= prefix +language + "BranchProfile_container.jsp"
    fieldBank = "NEWBANK";
    fieldBranch = "NEWBRANCH";
    fieldFlag = "NEWFLAG";
	CenterWindow(page,450,450,1);
  }
  
  function setDefBranch(){
    var row = document.forms[0].ROW.value;
    var bank = document.forms[0]["DBABNK"+row].value;
    if (document.forms[0]["DBAALL"+row].value =="*") {
    	page= prefix +language + "BranchProfile_container.jsp?DEFBANK=" + bank;
    	fieldBank = "NEWBANK";
    	fieldBranch = "NEWBRANCH";
    	fieldFlag = "NEWFLAG";
		CenterWindow(page,450,450,1);
	} else goAction('S');
  }
  
  function goAction(op){
  var ok;
  var row = document.forms[0].ROW.value;
     document.forms[0].ACTION.value = op;
     if (op == 'D') {
       if (document.forms[0]["DBABNK"+row].value == document.forms[0].DDBABNK.value) {
       	  if (document.forms[0]["DBABRN"+row].value == document.forms[0].DDBABRN.value) {
       	     alert("No se puede eliminar la sucursal por omision");
       	     return;
       	  } else if (document.forms[0]["DBAALL"+row].value =="*"){
       	  	 alert("La sucursal por omision pertenece al banco "+ document.forms[0]["DBABNK"+row].value +" .No se puede eliminar la sucursal por omision");
       	     return;
       	  }
       }      
       ok = confirm("La sucursal o sucursales selccionadas seran borradas"); 
       if (ok) document.forms[0].submit(); else document.forms[0].ACTION.value = "";
     } else {
       document.forms[0].submit();
     } 
  }
  
</SCRIPT>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }   
   
%> 
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="6">
  <INPUT TYPE=HIDDEN NAME="ACTION" value="">
  <INPUT TYPE=HIDDEN NAME="ROW" value="0">
  <INPUT TYPE=HIDDEN NAME="NEWBANK" value="">
  <INPUT TYPE=HIDDEN NAME="NEWBRANCH" value="0">
  <INPUT TYPE=HIDDEN NAME="NEWFLAG" value="">
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" align=right> 
              <b>Usuario :</b>
            </td>
            <td nowrap > 
               <input type="text" name="DBAUSR" size="14" maxlength="10" value="<%= branchBean.getDBAUSR()%>">
            </td>
          </tr>                    
        </table>
      </td>
    </tr>
  </table>
  <h4>Banco/Sucursal x Omision</h4>
  <table class="tbenter">
  	 <tr> 
         <td nowrap > 
            <input type="text" readonly name="DDBABNK" size="4" maxlength="2" value="<%= request.getParameter("defBNK")%>">
            <input type="text" readonly name="DDBABRN" size="4" maxlength="3" value="<%= request.getParameter("defBRN")%>">
            <input type="text" readonly name="DBRNNME" size="35" maxlength="30" value="<%= request.getParameter("defNME")%>">
         </td>
     </tr>
  </table>
  <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:getNewBranch()">Nueva</a> 
      </TD>
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:goAction('D')">Borrar</a> 
      </TD>
      <TD ALIGN=CENTER width="33%" class=TDBKG> <a href="javascript:setDefBranch()">Por Omision</a> 
      </TD>
     </TR>
  </TABLE>
  
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table id="TBBRANCH" cellspacing="0" cellpadding="2" width="100%" border="0">
          
          <tr id="trdark">
            <td nowrap width="1%"> 
              <div align="center">Sel.</div>
            </td>
            <td nowrap> 
              <div align="center">Banco</div>
            </td>
            <td nowrap> 
              <div align="center">Sucursal</div>
            </td>
            <td nowrap> 
              <div align="center">Descripcion</div>
            </td>
          </tr>
          <%
          String check ="checked";
          branchList.initRow();
   		  while (branchList.getNextRow()) {
          	branchBean = (CNTRLDBANME) branchList.getRecord();    	
          %>
          <tr id="trclear"> 
            <td nowrap> 
 	          	<INPUT type="radio" name="SELBRN" value="<%= branchList.getCurrentRow()%>" <%= check %> onClick="document.forms[0].ROW.value='<%= branchList.getCurrentRow()%>'">
 	            <INPUT type="hidden" name="DBAALL<%= branchList.getCurrentRow()%>" value="<%= branchBean.getDBAALL()%>">
 	        </td>
 	        <td nowrap align=center> 
 	          	<INPUT type="TEXT" readonly name="DBABNK<%= branchList.getCurrentRow()%>" value="<%= branchBean.getDBABNK()%>" size=4>
 	        </td>
 	        <td nowrap align=center> 
 	          	<INPUT type="TEXT" readonly name="DBABRN<%= branchList.getCurrentRow()%>" value="<%= branchBean.getDBABRN()%>" size=4>
 	        </td>
 	        <td nowrap > 
 	          	<INPUT type="TEXT" readonly name="BRNNME<%= branchList.getCurrentRow()%>" value="<%= branchBean.getBRNNME()%>" size=35>
 	        </td>
          </tr>
          <% 
           if (!check.equals("")) check="";
          } 
          %>
        </table>
      </td>
    </tr>
  </table>
 
  </form>
</body>
</html>
