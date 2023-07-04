<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User Branch</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<jsp:useBean id="groupList" class="datapro.eibs.beans.JBObjList" scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  
  function goAction(act) { 
  	var page ="";
    if (act == 'D') { //Delete
    	var collAll = document.frames("GRPFRAME").document.all;
    	var row = collAll("gpList").selectedIndex;    
     	if (collAll("gpList").options[row].value == "UNASSIGNED") {
        	alert("Este grupo no se puede Borrar");
        	return;
     	} else {
     	   page ="<%=request.getContextPath()%>/pages/s/GroupProfile_optgroup.jsp?ACT=" + act + "&ROW=" + row;
        }	
    } else { //New
        page ="<%=request.getContextPath()%>/pages/s/GroupProfile_optgroup.jsp?ACT=" + act;        
    }
    document.forms[0].ACTION.value = act;    
    document.all.OPTFRAME.style.display="";
    document.all.OPTFRAME.src = page;
  }
  
  function goUser() {
   var collAll = document.frames("USRFRAME").document.all;
   if (collAll("usrList")== null) {
     alert("No hay usuarios definidos");
   } else {
   		var val = collAll("usrList").options[collAll("usrList").selectedIndex].value;
   		if (val=="UNASSIGNED") val=" ";
   		var page ="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile?SCREEN=1&userid="+val;
   		CenterWindow(page,450,500,2);
   }
  }
  
  function goMenu() {
   var collAll = document.frames("GRPFRAME").document.all;
   var val = collAll("gpList").options[collAll("gpList").selectedIndex].value;
   var page ="<%=request.getContextPath()%>/security/menuAccess.do?userid="+val;
   CenterWindow(page,450,500,5);
  }
  
  function goMenu() {
   var page ="<%=request.getContextPath()%>/security/menuBasic.do";
   CenterWindow(page,600,500,5);
  }
  
  function goPrivilege() {
   var collAll = document.frames("GRPFRAME").document.all;
   var val = collAll("gpList").options[collAll("gpList").selectedIndex].value;
   if (val == "UNASSIGNED") {
        alert("Este grupo no puede tener Privilegios");
   } else {
   		var page ="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSGroupProfile?SCREEN=3&groupID="+val;
   		CenterWindow(page,450,400,2);
   }
  }
   
  function closeEnterGroup(){
   	  document.all.OPTFRAME.style.display="none";
  }
  
  document.onclick= closeEnterGroup;
  
  function setTableHeight() {
 	 var minValue= TBINFO.offsetTop + 20;
 	 var h = document.body.clientHeight - minValue ;
 	 TBINFO.style.height= h + ""; 
  }
  
</SCRIPT>

</head>
<body>
<h3 align="center"> Listado Grupo/Usuarios <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="maint, GroupProfile"></h3>
<hr size="4">
<form>
<INPUT TYPE=HIDDEN NAME="ACTION" value="N">
<IFRAME NAME=OPTFRAME SCROLLING=NO class="tableinfo" FRAMEBORDER=0 style="position: absolute; top: 65px; left: 170px; width: 200; height: 120; z-index: 3; display: none;"></IFRAME>
  <% if (groupList.getNoResult()) { %>
  
   <p align=center>
   		<h4 style="text-align:center"><b>No existen Grupos de Usuarios. 
            <br>Haga Click en al opcion Nuevo <br>para adicionar Grupo de usuarios
            </b>
        </h4>
   </p>
   <TABLE class="tbenter">
    <TR> 
      <TD ALIGN=CENTER class=TDBKG><div><a href="javascript:goAction('N')">Nuevo</a></div>
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:checkClose()">Salir</a> 
      </TD>
     </TR>
   </TABLE>
   
  <% } else { %> 
   
   <TABLE class="tbenter" id="TBOPTION">
    <TR> 
      <TD ALIGN=CENTER class=TDBKG><div>  <a href="javascript:goAction('N')">Nuevo<br>Grupo</a></div> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goMenu()">Menu<br>Opciones</a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goEditMenu()">Editar<br>Menu</a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG><div>  <a href="javascript:goAction('D')">Borrar<br>Grupo</a></div>  
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goPrivilege()">Privilegios<br>Grupo</a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:goUser()">Info.<br>Usuario</a> 
      </TD>
      <TD ALIGN=CENTER class=TDBKG> <a href="javascript:checkClose()">Salir</a> 
      </TD>
     </TR>
  </TABLE>
  			
  <table  class="tableinfo" id="TBINFO">
    <tr > 
      <td nowrap valign=top> 
        <table id="TBPVL" cellspacing="2" cellpadding="2" width="100%" border="0" HEIGHT=100%>
          
          <tr id="trdark">            
            <td nowrap WIDTH=30%> 
              <div align="center"><b>Grupos</b></div>
            </td>
            <td nowrap> 
              <div align="center"><b>Usuarios</b></div>
            </td>
          </tr>
          <tr> 
            <td nowrap align=center valign=top HEIGHT=100%>
 	          	<IFRAME NAME=GRPFRAME SCROLLING=AUTO FRAMEBORDER=0 ALLOWTRANSPARENCY=TRUE WIDTH=100% HEIGHT=100% SRC="<%=request.getContextPath()%>/pages/s/GroupProfile_grouplist.jsp" onfocus="closeEnterGroup()"></IFRAME>  	         	          	 	         	                 
 	        </td>
 	        <td nowrap align=center valign=top HEIGHT=100%>
 	          	<IFRAME NAME=USRFRAME SCROLLING=AUTO FRAMEBORDER=0 ALLOWTRANSPARENCY=TRUE WIDTH=90% HEIGHT=100% onfocus="closeEnterGroup()">Wait</IFRAME>  	         	          	
 	        </td>
          </tr>          
        </table>
      </td>
    </tr>
  </table>
  <SCRIPT language="JavaScript">  
  	setTableHeight();  
  	window.onresize=setTableHeight;     
  </SCRIPT>
 <% } %>
 </form>
 
</body>
</html>
