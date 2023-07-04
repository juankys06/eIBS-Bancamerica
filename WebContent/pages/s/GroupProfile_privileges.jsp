<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Branch</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<jsp:useBean id="grpPriviList" class="datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id= "groupID" class= "java.lang.String" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  
  function getNewPrivilege(){
    page= prefix +language + "PrivilProfile_container.jsp"
    fieldPVL = "NEWPVL";
    fieldVAL = "NEWVAL";
    CenterWindow(page,450,450,1);
  }
  
  function goModify(){
  var row = document.forms[0].ROW.value;
    page= prefix +language + "PrivilProfile_search.jsp?PARENT=GRP&ROW=" + row;
    fieldPVL = "NEWPVL";
    fieldVAL = "NEWVAL";
    CenterWindow(page,450,200,1);
  }
    
  function goAction(op){
  var ok = true;
  var row = document.forms[0].ROW.value;
     document.forms[0].ACTION.value = op;
     if (op == 'D') {    
       ok = confirm("El Privilegio seleccionado sera borrado"); 
       if (ok) document.forms[0].submit(); else document.forms[0].ACTION.value = "";
     } else {
       if (op == 'A') {
          try {
          	var coll = document.forms[0].SELPVL;          
          	if (coll.length > 1) {
            	for (i=0; i < coll.length;i++) {
                	if (coll[i].value.toUpperCase( ) == document.forms[0].NEWPVL.value.toUpperCase( )) {
            	  		ok = false;
            	  		break;
            		}
            	}
          	} else {
            	if (coll.value == document.forms[0].NEWPVL.value)  ok = false;
          	}
          } catch (e) {}
          if (!ok) alert("Este privilegio ya esta asignado");
       }
       if (ok) document.forms[0].submit();
     } 
  }
  
</SCRIPT>

</head>
<body>
<h3 align="center">Privilegios de Grupo<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="privileges, GroupProfile"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSGroupProfile" >
  <INPUT TYPE=HIDDEN NAME="SCREEN" value="4">
  <INPUT TYPE=HIDDEN NAME="ACTION" value="">
  <INPUT TYPE=HIDDEN NAME="ROW" value="0">
  <INPUT TYPE=HIDDEN NAME="NEWVAL" value="">
  <INPUT TYPE=HIDDEN NAME="NEWPVL" value="0">
  
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0" class="tbhead">
          <tr id="trdark"> 
            <td nowrap width="20%" align=right> 
              <b>Grupo :</b>
            </td>
            <td nowrap > 
               <input type="text" name="PVLUSR" size="14" maxlength="10" value="<%= groupID%>">
            </td>
          </tr>                    
        </table>
      </td>
    </tr>
  </table>
  <% if (grpPriviList.getNoResult()) { %>
  
  <p align=center>
  <h4 style="text-align:center"><b>El grupo <%= groupID %> no posee Privilegios asignados. 
            <br>Haga Click en al opcion Nuevo <br>para adicionar Privilegios
            </b></h4>
   </p>
   <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:getNewPrivilege()">Nuevo</a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:checkClose()">Salir</a> 
      </TD>
     </TR>
  </TABLE>
  <% } else { %>
  
 
  
  <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER width="25%" class=TDBKG> <a href="javascript:getNewPrivilege()">Nuevo</a> 
      </TD>
      <TD ALIGN=CENTER width="25%" class=TDBKG> <a href="javascript:goModify()">Modificar</a> 
      </TD>
      <TD ALIGN=CENTER width="25%" class=TDBKG> <a href="javascript:goAction('D')">Borrar</a> 
      </TD>
      <TD ALIGN=CENTER width="25%" class=TDBKG> <a href="javascript:checkClose()">Salir</a> 
      </TD>
     </TR>
  </TABLE>
  
  <table  class="tableinfo">
    <tr > 
      <td nowrap> 
        <table id="TBPVL" cellspacing="0" cellpadding="2" width="100%" border="0">
          
          <tr id="trdark">
            <td nowrap width="1%"> 
              <div align="center">Sel.</div>
            </td>
            <td nowrap> 
              <div align="center">Privilegio</div>
            </td>
            <td nowrap> 
              <div align="center">Valor</div>
            </td>
          </tr>
          <%
          String check ="checked";
          grpPriviList.initRow();
   		  while (grpPriviList.getNextRow()) {
          	CNTRLPVLNME priviBean = (CNTRLPVLNME) grpPriviList.getRecord();    	
          %>
          <tr id="trclear"> 
            <td nowrap> 
 	          	<INPUT type="radio" name="SELPVL" value="<%= priviBean.getPVLPVL()%>" <%= check %> onClick="document.forms[0].ROW.value='<%= grpPriviList.getCurrentRow()%>'">
 	        </td>
 	        <td nowrap align=left> 
 	          	<%= priviBean.getCNODSC()%>
 	        </td>
 	        <td nowrap align=center> 
 	          	<%= priviBean.getPVLVAL()%>
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
 
 <% } %>
  </form>
</body>
</html>
