<html>
<head>
<title>Operaciones Activas con Otras Instituciones Financieras</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>

</head>

<jsp:useBean id="trMant" class="datapro.eibs.beans.EGL003501Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body>
 
<H3 align="center">Operaciones Activas con Otras Instituciones Financieras<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="customer_enter,EDP0800"></H3>

<hr size="4">
<p>&nbsp;</p>

<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.creditproposal.JSEDP0800">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="200">
  </p>
  <h4>&nbsp;</h4>
  <table class="tbenter" cellspacing=0 cellpadding=3 width="100%" border="0">
    <tr> 
      <td width="30%"> 
        <div align="right">N&uacute;mero de Cliente : </div>
      </td>
      <td width="10%"> 
            <input type=TEXT name="E01DUECUN" size=10 maxlength=9 value="" onKeypress="enterInteger()"  value="<%= userPO.getCusNum().trim()%>" >
       		<a href="javascript:GetCustomerDescId('E01DUECUN','E01DUENA1','')">
       		<img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absmiddle" border="0" >
       		</a> 
      </td>
       <td width="30%"> 
      		<input type="text" name="E01DUENA1" size="35" maxlength="40" value="" readonly>
       </td>
    </tr>
  </table>
  
  <div align="center"> 
    <p><input id="EIBSBTN" type=submit name="Submit" value="Enviar"></p>
  </div>

<script language="JavaScript">
  document.forms[0].E01DUECUN.focus();
  document.forms[0].E01DUECUN.select();
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
