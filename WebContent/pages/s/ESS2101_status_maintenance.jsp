<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>Credit Card Status Maintenance</TITLE>
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<jsp:useBean id= "CCRStatusData" class= "datapro.eibs.beans.ESS210101Message" scope="session"/>
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"   scope="session"/>
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session"/>

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="javascript">
<!--

 function goBack() {
    document.forms[0].SCREEN.value = "100";
    document.forms[0].submit();
  }
  
</script>

</HEAD>

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 } else {
   if (userPO.getRedirect().equals("DO_PRINT")) {
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showReceipt()");
     out.println("</SCRIPT>"); 
     userPO.setRedirect("");    
   }
 } 
%>

<BODY bgcolor="#FFFFFF">

<FORM Method="post" Action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2101">
<INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="300">
<INPUT TYPE=HIDDEN NAME="E01DATA4" VALUE="<%= CCRStatusData.getE01DATA4().trim() %>">
  <h3 align="center">Estatu Tarjeta de Credito <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="status_list.jsp,ESS2101"> 
  </h3>
<hr size="4">
  
    <table class="tableinfo">
      <tr > 
        <td nowrap> 
        
 <table  cellspacing="0" cellpadding="2" width="100%" border="0" align="left">
    <tr id="trdark"> 
      <td nowrap width="40%" align="right"> Estatus de Cuenta :</td>
      <td align="left"> <INPUT NAME="E01ACCSTS" TYPE=TEXT VALUE="<%= CCRStatusData.getE01ACCSTS() %>" SIZE="5" MAXLENGTH="2" <%= CCRStatusData.getE01ACCSTS().trim().length()>0?"readonly":" " %>></td>
    </tr>
    <tr id="teclear"> 
      <td nowrap width="40%" align="right"> Estatus de Tarjeta:</td>
      <td align="left"> <INPUT NAME="E01CCRSTS" TYPE=TEXT VALUE="<%= CCRStatusData.getE01CCRSTS() %>" SIZE="5" MAXLENGTH="2" <%= CCRStatusData.getE01CCRSTS().trim().length()>0?"readonly":" " %>></td>
    </tr>
    
    <tr id="trdark">  
      <td nowrap width="40%" align="right"> Descripcion :</td>
      <td align="left"> <INPUT NAME="E01CCDSC" TYPE=TEXT VALUE="<%= CCRStatusData.getE01CCDSC()  %>" SIZE="35" MAXLENGTH="30"> </td>
    </tr>
  </table>
    
    </td>
   </tr>
</table>
<%
  String Label = CCRStatusData.getE01DATA4().trim().equals("D")?"Eliminar":CCRStatusData.getE01DATA4().trim().equals("U")?"Modificar":"Agregar";
%>
<table cellspacing="0" cellpadding="2" width="100%" border="0" align="center">
  <tr align="center">
    <td> <input id="EIBSBTN" type=submit name="Submit" value="<%= Label %>"></td>
    <td> <input id="EIBSBTN" value="Cancelar" onclick="javascript:goBack()"></td>
  </tr>
</table>    
</FORM>
</BODY>
</HTML>
