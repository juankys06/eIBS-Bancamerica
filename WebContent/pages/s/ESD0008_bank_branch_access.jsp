<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>>Customer Bills &amp; Account Payable Maintenance</title>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Page Designer V3.5.2 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<link Href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">


<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
</head>

<jsp:useBean id="user" class="datapro.eibs.beans.ESD000801Message"  scope="session" />
<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />
<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<body bgcolor="#FFFFFF">

<H3 align="center">Autorización Acceso Banco Agencia<img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="bank_branch_access, ESD0008"></H3>

<hr size="4">
<%
	String chkAll = "";
	String chkSlt = "";
	if (user.getE01DBAALL().equals("*")) {
		chkAll = "checked";
		chkSlt = "";
	} else {
		chkAll = "";
		chkSlt = "checked";	
	}
%>
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.security.JSESD0008">
  <p> 
    <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  </p>

  <table class="tableinfo"  cellspacing="0" cellpadding="2" width="100%" border="0">
    <tr id="trdark"> 
      <td nowrap width="40%"> 
      	<div align="right"> Identificación Usuario :</div>
      </td> 
      <td nowrap width="60%"> 
        <INPUT type="text" name="E01DBAUSR" size="16" maxlength="15" value="<%= user.getE01DBAUSR().trim()%>">
        <INPUT type="hidden" name="E01DSC" >
        <a href="javascript:GetUser('E01DBAUSR','E01DSC',document.forms[0].E01DBAUSR.value)"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="middle" border="0"></a> 
      </td>
    </tr> 
    <tr id="trclear"> 
      <td nowrap width="40%"> 
      	<div align="right"> Banco :</div>
      </td> 
      <td nowrap width="60%"> 
        <INPUT type="text" name="E01DBABNK" size="3" maxlength="2" value="<%= user.getE01DBABNK().trim()%>">
      </td>
    </tr>       
    <tr id="trdark"> 
      <td nowrap width="40%"> 
      	<div align="right">  Agencia :</div>
      </td> 
      <td nowrap width="60%"> 
        <input type="radio" name="E01DBAALL" value="*" <%= chkAll%> > Todas las Agencias
      </td>
    </tr>       
    <tr id="trclear"> 
      <td nowrap width="40%"> 
      	<div align="right"> </div>
      </td> 
      <td nowrap width="60%"> 
        <input type="radio" name="E01DBAALL" value="N" <%= chkSlt%> > Seleccione Sucursal
    	<input type="text" name="E01DBABRN" size="5" maxlength="4" value="<%= user.getE01DBABRN().trim()%>" onkeypress="enterInteger()">
    	<a href="javascript:GetBranch('E01DBABRN','')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt="help" align="absbottom" border="0"  ></a>
        
      </td>
    </tr>       
          
  </table>
 
 <p align="center"> 
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar" >
  </p>  
 
<% 
 if ( !error.getERRNUM().equals("0")  ) {
      error.setERRNUM("0");
 %>
     <SCRIPT Language="Javascript">;
            showErrors();
     </SCRIPT>
  <%
 }
%> 
</form>
</body>
</html>
