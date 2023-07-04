<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<META name="GENERATOR" content="IBM WebSphere Page Designer V4.0 for Windows">
<META http-equiv="Content-Style-Type" content="text/css">
<title>Calificación de Clientes</title>
<link href="<%=request.getContextPath()%>/pages/style.css" rel="stylesheet">
<jsp:useBean id= "clasif" class= "datapro.eibs.beans.EDL053001Message"  scope="session" />

<jsp:useBean id= "error" class= "datapro.eibs.beans.ELEERRMessage"  scope="session" />

<jsp:useBean id= "userPO" class= "datapro.eibs.beans.UserPos"  scope="session" />

<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/eIBS.jsp"> </SCRIPT>
<script language="Javascript1.1" src="<%=request.getContextPath()%>/pages/s/javascripts/optMenu.jsp"> </SCRIPT>

<%
 String title="";
 if ( userPO.getOption().equals("CLIENT_C") ) {
    title="Jurídico";
%>

   <SCRIPT Language="Javascript">
       builtNewMenu(client_corp_opt);       
   </SCRIPT>

<%
} else if ( userPO.getOption().equals("CLIENT_P") ) { 
  title="Personal";
%>
	<SCRIPT Language="Javascript">
       builtNewMenu(client_personal_opt);       
   </SCRIPT>
<%
}  
%> 
</head>

<body bgcolor="#FFFFFF">

<% 
 if ( !error.getERRNUM().equals("0")  ) {
     error.setERRNUM("0");
     out.println("<SCRIPT Language=\"Javascript\">");
     out.println("       showErrors()");
     out.println("</SCRIPT>");
 }
 if ( !userPO.getOption().equals("CLASIF") ) {
    out.println("<SCRIPT>initMenu();</SCRIPT>");
 }
 %> 
 
 <h3 align="center">Tipo de Relación y Clasificación de Cliente <%= title %>  <img src="<%=request.getContextPath()%>/images/e_ibs.gif" align="left" name="EIBS_GIF" ALT="client_clasif, EDL0530" ></h3>
<hr size="4">
<form method="post" action="<%=request.getContextPath()%>/servlet/datapro.eibs.client.JSEDL0530">
  <INPUT TYPE=HIDDEN NAME="SCREEN" VALUE="2">
  
  	<table class="tableinfo">
      <tr > 
        <td nowrap>          
         <table cellspacing="0" cellpadding="2" width="100%" border="0">
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Identificaci&oacute;n :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly name="E01CUSIDN" size="10" maxlength="9" value="<%= clasif.getE01CUSIDN()%>">
            </td>
            <td nowrap> 
              <div align="right">No Cliente :</div>
            </td>
            <td nowrap> 
              <input type="text" readonly name="E01CUSCUN" size="10" maxlength="9" value="<%= clasif.getE01CUSCUN()%>">
            </td>
          </tr>
          <tr id="trdark"> 
            <td nowrap> 
              <div align="right">Nombre :</div>
            </td>
            <td nowrap colspan="3"> 
              <input type="text" readonly name="E01CUSNA1" size="46" maxlength="45" value="<%= clasif.getE01CUSNA1().trim()%>">
            </td>
          </tr>          
         </table>
        </td>
      </tr>
    </table>
    
    <br>
	<table class="tableinfo">
      <tr > 
        <td nowrap> 
          <table cellspacing="2" cellpadding="2" width="100%" border="0" align="left">
            <tr id=trclear> 
              <td nowrap width="30%"> 
                <div align="right">Tipo de Relación :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E01CUSUC1" size="5" maxlength="4" value="<%= clasif.getE01CUSUC1().trim()%>" >
                <input type="text" name="D01CUSUC1" size="36" maxlength="35" value="<%= clasif.getD01CUSUC1().trim()%>" readonly>
                <a href="javascript:GetCodeDescCNOFC('E01CUSUC1','D01CUSUC1','2A')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
			  </td>
            </tr>
            <tr id=trdark> 
              <td nowrap width="30%"> 
                <div align="right">Clasificaci&oacute;n :</div>
              </td>
              <td nowrap width="70%"> 
                <input type="text" name="E01CUSUC2" size="5" maxlength="4" value="<%= clasif.getE01CUSUC2().trim()%>" >
                <input type="text" name="D01CUSUC2" size="36" maxlength="35" value="<%= clasif.getD01CUSUC2().trim()%>" readonly>
                <a href="javascript:GetCodeDescCNOFC('E01CUSUC2','D01CUSUC2','2B')"><img src="<%=request.getContextPath()%>/images/1b.gif" alt=". . ." align="absbottom" border="0" ></a> 
              <IMG src="<%=request.getContextPath()%>/images/Check.gif" alt="mandatory field" align="absbottom" border="0">
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
<p>
  <center>
    <input id="EIBSBTN" type=submit name="Submit" value="Enviar">
  </center>
</P>
</form>
</body>
</html>
