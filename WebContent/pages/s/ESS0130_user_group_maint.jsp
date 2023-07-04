<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<%@ page import = "datapro.eibs.master.Util" %>
<head>
<title>Mantenimiento Grupos de Usuario</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">

<script Language="Javascript1.1">

var flagDelete = false;

function del() {
  document.forms[0].E01OPETYP.value='0004';
  flagDelete=true;
}


function update() {
  document.forms[0].E01OPETYP.value='0005';
  flagDelete=false;
}



function save() {
  document.forms[0].E01OPETYP.value='0001';
  flagDelete=false;
}


function checkDelete(){
  if (flagDelete)
    return confirm("¿Está seguro que desea eliminar este registro?");
  else
    return true;  
}

  
</script>


<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<jsp:useBean id="user" class="datapro.eibs.beans.ESS013001Message"  scope="session" />
<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="userPO" class="datapro.eibs.beans.UserPos" scope="session" />





<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>



</head>

<body bgcolor="#FFFFFF">

 <% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0") ;
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
%>

<h3 align="center">Acceso de Usuarios<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="User Group Maintenance, ESS0130" ></h3>
<hr size="4">
 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESS0130" onsubmit="checkDelete()" >
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="3">
    <INPUT TYPE="HIDDEN" NAME="E01OPETYP" VALUE="0005" />
  </p>
  <h4>Información de Grupo </h4>
  <table  class="tableinfo">
    <tr > 
      <td nowrap width="620"> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap width="40"> 
              <div align="right"> </div>
            </td>
            <td nowrap width="151" align="right"> Grupo Usuario : </td> 
            
            <%if (request.getParameter("E01OPETYP").equals("0001")) { %>
 
            <td nowrap width="175" > 
              <input type="text" name="E01USEUID" size="17" maxlength="15" value="">
            </td>
            <%} else { %>
             <td nowrap width="175">
                <input type="hidden" name="E01USEUID" size="17" maxlength="15" value="<%= user.getE01USEUID().trim()%>" >
    			<div align="left"><%= user.getE01USEUID().trim()%></div>
			</td>
			 <% } %>
            <td nowrap> 
             </td>  
            
          </tr>
          <tr id="trdark"> 
            <td nowrap width="40"> 
              <div align="right"> </div>
            </td>
            <td nowrap width="151" align="right"> 
              	
              	 
             Nombre Grupo : </td> 
            <td nowrap width="175"><div align="right"><INPUT type="text"
					name="E01USECLS" size="40" maxlength="45"
					value="<%= user.getE01USECLS().trim()%>"></div>
            </td>
            <td nowrap>
             <IMG src="<%=request.getContextPath()%>/images/Check.gif"
					alt="mandatory field" align="absbottom" border="0"></td>  
          </tr>
         </table>
      </td>
    </tr>
  </table>
  
  <br>
   <p align="center">
   <%if (!request.getParameter("E01OPETYP").equals("0001")) { %>
    <input type="submit" id="EIBSBTN"  name="Update" value="Actualizar" onclick="update()">
   <INPUT  type="submit" id="EIBSBTN" name="Delete" value="Eliminar" onclick="del()">
   <%} else { %>
     <INPUT  id="EIBSBTN" type="submit" name="Save" value="Guardar" onclick="save()">
   <% } %>
   </p>
</form>
</body>
</html>

