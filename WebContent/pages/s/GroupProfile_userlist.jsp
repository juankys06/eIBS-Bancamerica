<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>User List</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<%@ page import = "java.io.*,java.net.*,datapro.eibs.beans.*,com.datapro.eibs.security.bean.*,java.math.*" %>

<jsp:useBean id="userList" class="datapro.eibs.beans.JBObjList"  scope="session" />

<jsp:useBean id="lstGrp" class= "java.lang.String"  scope="session" />
<jsp:useBean id="groupID" class= "java.lang.String"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<SCRIPT Language="Javascript">
  function showTab(index){  
  	for(var i=0;i<3;i++){
   		document.all["Tab"+i].className="tabnormal";
   		document.all["dataTab"+i].style.display="none";
   	}
  
    document.all["Tab"+index].className="tabhighlight";  
  	document.all["dataTab"+index].style.display="";
  	document.forms[0].ACTION.value = index;
  }
  
  function selUser(idx) {
    document.forms[0].DELUSER.value = document.forms[0].usrList.options[idx].value;
	document.forms[0].MOVUSER.value = document.forms[0].usrList.options[idx].value;
	document.forms[0].ROW.value = "" + idx; 
  } 
  
  function checkValues() {
    var usr ="";
    var grp = "";
    if (document.forms[0].ACTION.value=="0") { //New
       usr = replaceAll(document.forms[0].NEWUSER.value,' ');
       if (usr.length < 1) {
         alert("El nombre del usuario no puede estar en blanco");
         document.forms[0].NEWUSER.focus();
         return false;
       }
       grp = document.forms[0].GROUPID.value;
       //grp = document.forms[0].NEWSEL.options[document.forms[0].NEWSEL.selectedIndex].value;
       //document.forms[0].ROWGRP.value = "" + document.forms[0].NEWSEL.selectedIndex;
    } else if (document.forms[0].ACTION.value=="1") { //Delete
       var ok=confirm("Esta opcion borrara al usuario " + document.forms[0].DELUSER.value);
       if (!ok) return false;
       usr = document.forms[0].DELUSER.value;
       grp = document.forms[0].GROUPID.value;
    } else { //Move
       usr = document.forms[0].MOVUSER.value;
       grp = document.forms[0].MOVSEL.options[document.forms[0].MOVSEL.selectedIndex].value;    
       document.forms[0].ROWGRP.value = "" + document.forms[0].MOVSEL.selectedIndex;
       if (grp == document.forms[0].GROUPID.value ) return false;
    }
    if (grp=="UNASSIGNED") grp=" ";
    if (document.forms[0].ACTION.value == "1") document.forms[0].target = "USRFRAME"; else document.forms[0].target = "GRPFRAME"; 
    document.forms[0].GROUPID.value = grp;
    document.forms[0].USERID.value = usr;
    return true; 
  }
</SCRIPT>

</head>
<body style = "background-color :transparent; margin:0">
  <form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.security.servlet.JSUserProfile" onsubmit="return(checkValues())" > 
    <INPUT TYPE=HIDDEN NAME="ACTION" value="0">
	<INPUT TYPE=HIDDEN NAME="SCREEN" value="8">
	<INPUT TYPE=HIDDEN NAME="GROUPID" value="<%=groupID%>">
	<INPUT TYPE=HIDDEN NAME="USERID" value="">
	<INPUT TYPE=HIDDEN NAME="ROW" value="0">
	<INPUT TYPE=HIDDEN NAME="ROWGRP" value="0">
    <table width="100%" id=TBINFO>
    <tr>
    	<td colspan=2>
    	 <b> Grupo : <% if (groupID.trim().equals("")) out.print("SIN DEFINICION"); else out.print(groupID);%></b>
    	</td>
    </tr>
    <%  if (userList.getNoResult()) { %> 
    <tr>
    	<td>
    	 <b> No hay usuarios asignados</b>    	 
    	</td>
    </tr>
    <tr>
    <% } else {
         CNTRLBTH bthBean = null;   		 
     %>        
    
    <tr> 
        <td align=center valign=top width="30%">
        <b>Usuarios :</b>
  	    <select id="usrList" size=1 style="width:150" onclick="selUser(this.selectedIndex)">
  	      <%
          userList.initRow();
   		  while (userList.getNextRow()) {
          	bthBean = (CNTRLBTH) userList.getRecord();    	
          %>
              <option value="<%= bthBean.getBTHKEY()%>"><%= bthBean.getBTHKEY()%></option>	          	
 	        <%            
          } 
          //var sizeList = userList.getLastRec() + 1;
          %>  
       </select>
       </td>
       <%            
       } 
    %>
       <td align=center valign=top width="70%"> 	         
           		<table id="TableTab" cellspacing=0 cellpadding=2 border="0">
                	<tr> 
                    <td nowrap id="Tab0" class="tabhighlight" onClick="showTab(0)"> 
                            <div nowrap>Nuevo</div>
                    </td>
                    <%  if (!userList.getNoResult()) { %> 
                    <td nowrap id="Tab1" class="tabnormal" <%  if (!userList.getNoResult()) out.print("onClick=\"showTab(1)\""); %>> 
                            <div nowrap>Borrar</div>
                    </td> 
                    <td nowrap id="Tab2" class="tabnormal" onClick="showTab(2)"> 
                            <div nowrap>Mover</div>
                    </td> 
                    <% } %>
                    <td class="tabempty" style="background-color:transparent" width="100%">&nbsp;                       
                    </td> 
                  </tr>
            	</table>
  				<table class="tabdata" style="filter:" width="100%">
    			  <tr>
     				<td nowrap> 
						<div id="dataTab0">
			   				<table cellspacing="0" width="100%">
							<tr><td class="text">Nuevo usuario:</td></tr>
							<tr><td><input id="NEWUSER" type="text" class="text" maxlength=10></td></tr>
							<!--<tr><td class="text">en grupo:</td></tr>
							<tr><td>
								<select id="NEWSEL" class="text">
									<%= lstGrp %>
								</select>
								</td>
							</tr>-->
			    			</table>				
						</div>
						<%  if (!userList.getNoResult()) { %> 
						<div id="dataTab1" style="display:none">
			   				<table cellspacing="0" width="100%">
							<tr><td class="text">Usuario :</td></tr>
							<tr><td>
								<input id="DELUSER" type="text" class="text" readonly></td></tr>							
							</table>
						</div>
						<div id="dataTab2" style="display:none">
   							<table cellspacing="0" width="100%">
							<tr><td class="text">Mover usuario:</td></tr>
							<tr><td>
								<input id="MOVUSER" type="text" class="text" readonly></td></tr>	
							<tr><td class="text">al grupo:</td></tr>
							<tr><td>
								<select id="MOVSEL" class="text">
								<%= lstGrp %>
								</select>
								</td></tr>
    						</table>
						</div>
						<%  } %> 		
					</td>
    			</tr>
  				</table>
  				<p align=center>
					<input type="submit" value="Enviar" class="text">
				</p>
 	  </td>
 	  </tr>
 	  </table>
    
   
  </form>
  <%  if (!userList.getNoResult()) { %> 
  <SCRIPT Language="Javascript">  
  	function setUListHeight() {
  	 var sizeList = document.forms[0].usrList.options.length;
 	 var h= document.body.clientHeight;
 	 h = h / 20;
 	 document.forms[0].usrList.size = (h > sizeList) ? sizeList: Math.round(h + 1); 
  	}
    setUListHeight();  
  	window.onresize=setUListHeight;
  	var collAll = parent.document.frames("GRPFRAME").document.all;
    var idx = collAll("gpList").selectedIndex;
  
  	document.forms[0].MOVSEL.selectedIndex = idx;
    document.forms[0].usrList.selectedIndex=0;
    selUser(0);
    
  </SCRIPT>
  <% } %> 
</body>
</html>
