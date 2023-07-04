<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User List</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<jsp:useBean id="lstGrp" class= "java.lang.String"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
   function checkValues() {
    var usr ="";
    var grp = "";
    if (document.forms[0].ACTION.value=="N") { //New       
       grp = document.forms[0].GROUPNAME.value;
       if (trim(grp) == "") {
         alert("Teclee un Nombre de Grupo valido");
         return false;
       } else {
          for ( i=0; i < document.forms[0].SELGROUP.options.length; i++){
             
             if (grp.toUpperCase( ) == document.forms[0].SELGROUP.options[document.forms[0].SELGROUP.selectedIndex].value ) {
                alert("El grupo " + grp.toUpperCase( ) + " ya existe");
                return false;
             }
          } 
       }
    } else { //Delete
       var ok = confirm("El Grupo seleccionado sera borrado"); 
       if (!ok) return false;
       grp = document.forms[0].SELGROUP.options[document.forms[0].SELGROUP.selectedIndex].value;    
       document.forms[0].ROW.value = "" + document.forms[0].SELGROUP.selectedIndex;
       if (grp == document.forms[0].GROUPID.value ) return false;
       if (document.forms[0].SELUSER.checked) document.forms[0].USERID.value = "ALL"; else document.forms[0].USERID.value = "";
    }
    
    if (grp=="UNASSIGNED") {
      alert("Este Grupo no puede ser borrado");
      return false;
    }
    
    document.forms[0].GROUPID.value = grp;
    
    return true; 
  }
</SCRIPT>

</head>
<body style = "background-color :transparent; margin:4">
  <%
     int row = 0;
          try {
      		row = Integer.parseInt(request.getParameter("ROW"));
    	  }
          catch (Exception e) {
           row = 0;
          }
     String act = "";
         try {
      		act = request.getParameter("ACT");
    	  }
          catch (Exception e) {
           act = "N";
          }
  %>
  <form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSGroupProfile" onsubmit="return(checkValues())" target="_parent"> 
    <INPUT TYPE=HIDDEN NAME="ACTION" value="<%= act %>">
	<INPUT TYPE=HIDDEN NAME="SCREEN" value="8">
	<INPUT TYPE=HIDDEN NAME="GROUPID" value="">
	<INPUT TYPE=HIDDEN NAME="USERID" value="">
	<INPUT TYPE=HIDDEN NAME="ROW" value="<%= row%>">
   <CENTER> 
    <div id="newGroup" <% if (act.equals("D")) out.print("style=\"display: none\"");%>>    
	<table cellspacing="0" width="100%" style = "background-color :transparent;">
		<tr>
			<td class="text">Nuevo grupo:</td>
		</tr>
		<tr>
		<td><input id="GROUPNAME" type="text" class="text" maxlength=10> </td>
	</tr>
	</table>
	
	</div>
	<div id="deleteGroup" <% if (act.equals("N")) out.print("style=\"display: none\"");%>>
	<table cellspacing="0" width="100%" style = "background-color :transparent;">
		<tr>
			<td class="text">Borrar Grupo:</td>
		</tr>
		<tr>
			<td>
			<select id="SELGROUP" class="text">
			  <%= lstGrp %>
			</select> 
			</td>
		</tr>
		<tr>
		<td class="text">
			<input id="SELUSER" type="checkbox">Borrar todos los Usuarios 
		</td>
	</tr>
	</table>
	</div>
	<p align=center>
	   <input type="submit"	value="enviar" class="text">&nbsp;&nbsp;
	   <input type="button"	value="cancelar" class="text" onclick="parent.closeEnterGroup()">
	</p>
   </CENTER>
  </form>
  <SCRIPT Language="Javascript">
     document.forms[0].SELGROUP.selectedIndex = <%= row %>;     
  </SCRIPT>
</body>
</html>
