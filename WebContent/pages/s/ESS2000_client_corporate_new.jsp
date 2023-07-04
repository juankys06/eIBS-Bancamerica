<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>Corporate User</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

<jsp:useBean id="error" class="datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id="cusdata" class="datapro.eibs.beans.ESS200001Message" scope="session" />
<script language="Javascript1.1">
function GetCustomerUserId(name, type){
	page= prefix + language + "ESS0005_client_help_container.jsp";
	fieldName=name;
	fieldDesc=type;
	fieldId="";
	fieldAux1=name;
	CenterWindow(page,530,530,1);
}
</script>
</head>

<body>

 <h3 align="center">Nuevo/Mantenimiento Usuario Banca Corporativa<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" alt="client_corporate_new, ESS2000"></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/com.datapro.eibs.internet.JSESS2000">
  <p>
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="102">
    <INPUT TYPE=HIDDEN NAME="TYPE" VALUE="1">
  </p>
  <h4 align="center">Ingrese ID de Entidad</h4>
  <p>&nbsp; </p>
  <table class="tableinfo">
    <tr> 
      <td nowrap> 
        <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap align="right" valign="middle" width="49%">Entidad :</td>
            <td nowrap align="left" valign="middle"  width="51%">
              <INPUT type="text" name="CUSID" value="<%= cusdata.getE01EUSUSR().trim()%>" maxlength="10" size="10">
              <a href="javascript:GetCustomerUserId('CUSID','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="bottom" border="0" ></a>
            </td>
          </tr>
        </table>
      </td>
    </tr>
  </table>
  <p align="center"> 
   <input id="EIBSBTN" type=submit name="Submit" value="Aceptar">
  </p>
<script language="JavaScript">
  document.forms[0].CUSID.focus();
  document.forms[0].CUSID.select();
</script>
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">
            showErrors();
     </SCRIPT>
 <%
 }
%>
</form>
</body>
</html>
